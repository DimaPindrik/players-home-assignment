package com.example.demo.service;

import com.example.demo.exception.PlayerNotFoundException;
import com.example.demo.exception.ServiceException;
import com.example.demo.loader.PlayerCSVLoader;
import com.example.demo.model.PlayerDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Slf4j
public class PlayersServiceImpl implements PlayersService {

  private final PlayerCSVLoader playerCsvLoader;

  public List<PlayerDTO> getAllPlayers() {
    try {
      return playerCsvLoader.getPlayersList();
    } catch (Exception e) {
      log.error("Exception occurred while executing getAllPlayers", e);
      throw new ServiceException(e);
    }
  }

  public PlayerDTO getPlayerById(String playerId) {
    try {
      Map<String, PlayerDTO> playersMap = playerCsvLoader.getPlayersMap();

      if (!playersMap.containsKey(playerId)) {
        throw new PlayerNotFoundException("Player with ID: " + playerId + " not found");
      }

      return playersMap.get(playerId);
    } catch (Exception e) {
      log.error("Exception occurred while executing getPlayerById with id {}", playerId, e);
      throw new ServiceException(e);
    }
  }
}
