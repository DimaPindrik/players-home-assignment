package com.example.demo.loader;

import com.example.demo.mapper.PlayerMapper;
import com.example.demo.model.PlayerDTO;
import com.example.demo.utils.CSVReaderUtil;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class PlayerCSVLoader {

  private final String CSV_PATH = "csv/player.csv";

  private final PlayerMapper playerMapper;
  private Map<String, PlayerDTO> playersMap;

  @PostConstruct
  private void initCsvMap() {
    try {
      List<String[]> csvRows = CSVReaderUtil.readAllLines(CSV_PATH);
      playersMap = playerMapper.transformToMap(csvRows);
      System.out.println("A");
    } catch (Exception e) {
      throw new RuntimeException("Error while loading CSV file: " + CSV_PATH, e);
    }
  }

  public Map<String, PlayerDTO> getPlayersMap() {
    return playersMap;
  }

  public List<PlayerDTO> getPlayersList() {
    return playersMap.values().stream().toList();
  }
}
