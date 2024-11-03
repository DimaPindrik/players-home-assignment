package com.example.demo.controller;

import com.example.demo.model.PlayerDTO;
import com.example.demo.service.PlayersService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/players")
public class PlayersController {

  private final PlayersService playersService;

  @GetMapping(value = "/")
  @Operation(summary = "Returns the list of all players")
  public ResponseEntity<List<PlayerDTO>> getAllPlayers() {
    return ResponseEntity.ok(playersService.getAllPlayers());
  }

  @GetMapping(value = "{playerId}")
  @Operation(summary = "Returns a single player by ID")
  public ResponseEntity<PlayerDTO> getPlayerById(@PathVariable String playerId) {
    return ResponseEntity.ok(playersService.getPlayerById(playerId));
  }
}
