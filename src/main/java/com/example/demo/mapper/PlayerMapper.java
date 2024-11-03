package com.example.demo.mapper;

import com.example.demo.model.PlayerDTO;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class PlayerMapper {

  public List<PlayerDTO> transformToList(List<String[]> rows) {
    return rows.stream()
            .map(this::transform).toList();
  }

  public Map<String, PlayerDTO> transformToMap(List<String[]> rows) {
    return transformToList(rows).stream()
            .collect(Collectors.toMap(PlayerDTO::getId, dto -> dto));
  }

  private PlayerDTO transform(String[] row) {
    List<String> csvRow = Arrays.stream(row).toList();
    PlayerDTO.PlayerDTOBuilder builder = PlayerDTO.builder();
    builder.id(csvRow.get(0));

    builder.birthYear(parseIntCol(csvRow.get(1)));
    builder.birthMonth(parseIntCol(csvRow.get(2)));
    builder.birthDay(parseIntCol(csvRow.get(3)));
    builder.birthCountry(csvRow.get(4));
    builder.birthState(csvRow.get(5));
    builder.birthCity(csvRow.get(6));

    builder.deathYear(parseIntCol(csvRow.get(7)));
    builder.deathMonth(parseIntCol(csvRow.get(8)));
    builder.deathDay(parseIntCol(csvRow.get(9)));
    builder.deathCountry(csvRow.get(10));
    builder.deathState(csvRow.get(11));
    builder.deathCity(csvRow.get(12));

    builder.nameFirst(csvRow.get(13));
    builder.nameLast(csvRow.get(14));
    builder.nameGiven(csvRow.get(15));

    builder.weight(parseIntCol(csvRow.get(16)));
    builder.height(parseIntCol(csvRow.get(17)));
    builder.bats_(csvRow.get(19));
    builder.throws_(csvRow.get(19));
    builder.debut(parseDateCol(csvRow.get(20)));
    builder.finalGame(parseDateCol(csvRow.get(21)));

    builder.retroId(csvRow.get(22));
    builder.bbrefId(csvRow.get(23));
    return builder.build();
  }

  private Integer parseIntCol(String row) {
    return !row.isEmpty() ? Integer.valueOf(row) : null;
  }

  private LocalDate parseDateCol(String row) {
    return !row.isEmpty() ? LocalDate.parse(row) : null;
  }
}
