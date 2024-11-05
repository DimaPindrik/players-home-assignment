package com.example.demo.service;

import com.example.demo.model.PlayerDTO;

import java.util.List;

public interface PlayersService {


  List<PlayerDTO> getAllPlayers();

  PlayerDTO getPlayerById(String playerId);
}
