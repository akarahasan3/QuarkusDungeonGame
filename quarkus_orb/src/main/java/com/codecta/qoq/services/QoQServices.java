package com.codecta.qoq.services;

import com.codecta.qoq.services.dto.DungeonDto;
import com.codecta.qoq.services.dto.GameMapDto;
import com.codecta.qoq.services.dto.PlayerDto;

public interface QoQServices {

    GameMapDto createMap();

    GameMapDto getMapById(Integer id);

    DungeonDto getNextDungeon(GameMapDto gameMapDto);

    PlayerDto findPlayerById(Integer id);

    PlayerDto heal(PlayerDto playerDto);
}
