package com.example.demo.service;

import com.example.demo.exception.PlayerNotFoundException;
import com.example.demo.mapper.PlayerMapper;
import com.example.demo.model.PlayerDTO;
import com.example.demo.utils.CSVReaderUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class PlayersService {
  private final String PLAYERS_CSV_PATH = "csv/player.csv";

  private final PlayerMapper playerMapper;
  private final CacheService<PlayerDTO> cacheService;

  @Cacheable(value = CacheService.CACHE_NAME, key = "'allPlayers'")
  public List<PlayerDTO> getAllPlayers() {
    try {
      List<String[]> csvRows = CSVReaderUtil.readAllLines(PLAYERS_CSV_PATH);
      return playerMapper.transformToList(csvRows);
    } catch (Exception e) {
      log.error("Exception occurred while executing getAllPlayers", e);
      throw new RuntimeException(e);
    }
  }

  public PlayerDTO getPlayerById(String playerId) {
    Map<String, PlayerDTO> playersMap;
    try {
      if (cacheService.isCacheEmpty()) {
        List<String[]> csvRows = CSVReaderUtil.readAllLines(PLAYERS_CSV_PATH);
        playersMap = playerMapper.transformToMap(csvRows);
      } else {
        List<PlayerDTO> cachedResult = cacheService.getCachedResult();
        playersMap = cachedResult.stream()
                .collect(Collectors.toMap(PlayerDTO::getId, dto -> dto));
      }

      if (!playersMap.containsKey(playerId)) {
        throw new PlayerNotFoundException("Player with ID: " + playerId + " not found");
      }

      return playersMap.get(playerId);
    } catch (Exception e) {
      log.error("Exception occurred while executing getPlayerById with id {}", playerId, e);
      throw new RuntimeException(e);
    }
  }
}
