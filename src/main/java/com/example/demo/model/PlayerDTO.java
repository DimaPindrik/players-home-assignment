package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PlayerDTO {

  @JsonProperty("playerId")
  private String id;

  private Integer birthYear;
  private Integer birthMonth;
  private Integer birthDay;
  private String birthCountry;
  private String birthState;
  private String birthCity;

  private Integer deathYear;
  private Integer deathMonth;
  private Integer deathDay;
  private String deathCountry;
  private String deathState;
  private String deathCity;

  private String nameFirst;
  private String nameLast;
  private String nameGiven;

  private Integer weight;
  private Integer height;

  @JsonProperty("bats")
  private String bats_;
  @JsonProperty("throws")
  private String throws_;

  private LocalDate debut;
  private LocalDate finalGame;

  private String retroId;
  private String bbrefId;
}
