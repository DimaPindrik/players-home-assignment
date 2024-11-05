package com.example.demo.service;


import com.example.demo.loader.PlayerCSVLoader;
import com.example.demo.model.PlayerDTO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PlayersServiceTest {

  @InjectMocks private PlayersService playersService;
  @Mock private PlayerCSVLoader playerCsvLoader;

  @Test
  void when_getAllPlayers_expectSuccess() {
    List<PlayerDTO> expectedResult = new ArrayList<>(); // init with values
    Mockito.when(playerCsvLoader.getPlayersList()).thenReturn(expectedResult);

    List<PlayerDTO> actualResult = playersService.getAllPlayers();

    verify(playerCsvLoader, times(1)).getPlayersList();
    assertEquals(expectedResult, actualResult);
  }

  @Test
  void when_getAllPlayersFails_expectFailure() {
    doThrow(new RuntimeException()).when(playerCsvLoader).getPlayersList();

    assertThrows(
            RuntimeException.class,
            () -> playersService.getAllPlayers());
  }

  //TODO: add getPlayerById successful test

  //TODO: add getPlayerById fail test when id not found

  //TODO: add getPlayerById fail test cache fails

  //TODO: add getPlayerById fail test when mapper fails
}
