package com.example.demo.service;


import com.example.demo.mapper.PlayerMapper;
import com.example.demo.model.PlayerDTO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.internal.matchers.Any;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PlayersServiceTest {

  @InjectMocks private PlayersService playersService;
  @Mock private PlayerMapper playerMapper;
  @Mock private CacheService cacheService;

  @Test
  void when_getAllPlayers_expectSuccess() {
    List<PlayerDTO> expectedResult = new ArrayList<>();
    Mockito.when(playerMapper.transformToList(any())).thenReturn(expectedResult);

    List<PlayerDTO> actualResult = playersService.getAllPlayers();

    verify(playerMapper, times(1)).transformToList(any());
    assertEquals(expectedResult, actualResult);
  }

  @Test
  void when_getAllPlayersFails_expectFailure() {
    doThrow(new RuntimeException()).when(playerMapper).transformToList(any());

    assertThrows(
            RuntimeException.class,
            () -> playersService.getAllPlayers());
  }

  //TODO: add getPlayerById successful test

  //TODO: add getPlayerById fail test when id not found

  //TODO: add getPlayerById fail test cache fails

  //TODO: add getPlayerById fail test when mapper fails
}
