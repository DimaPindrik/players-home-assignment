package com.example.demo.e2e;

import com.example.demo.exception.ServiceException;
import com.example.demo.model.PlayerDTO;
import com.example.demo.service.PlayersService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class PlayersServiceE2ETest {

  private static final String TEST_ID = "TEST_ID";

  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private PlayersService playersService;

  @Test
  void shouldReturnAllPlayers() throws Exception {

    List<PlayerDTO> expectedResult = new ArrayList<>();
    expectedResult.add(PlayerDTO.builder().build());
    when(playersService.getAllPlayers()).thenReturn(expectedResult);

    ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.get("/api/players/")
            .accept(MediaType.APPLICATION_JSON));

    resultActions
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.length()").value(1));
  }

  @Test
  void shouldReturnPlayerById() throws Exception {

    PlayerDTO expectedResult = PlayerDTO.builder().id(TEST_ID).build();
    when(playersService.getPlayerById(TEST_ID)).thenReturn(expectedResult);

    ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.get("/api/players/" + TEST_ID)
            .accept(MediaType.APPLICATION_JSON));

    resultActions
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.playerId").value(TEST_ID));
  }

  @Test
  void shouldThrowExceptionInGetAllPlayers() throws Exception {

    doThrow(new ServiceException("INTERNAL ERROR")).when(playersService).getAllPlayers();

    ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.get("/api/players/")
            .accept(MediaType.APPLICATION_JSON));

    resultActions
            .andExpect(status().is5xxServerError());
  }

  @Test
  void shouldThrowExceptionInGetPlayerById() throws Exception {

    doThrow(new ServiceException("INTERNAL ERROR")).when(playersService).getPlayerById(TEST_ID);

    ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.get("/api/players/" + TEST_ID)
            .accept(MediaType.APPLICATION_JSON));

    resultActions
            .andExpect(status().is5xxServerError());
  }
}
