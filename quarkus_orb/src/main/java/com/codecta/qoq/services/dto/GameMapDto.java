package com.codecta.qoq.services.dto;

import java.util.List;

public class GameMapDto {
    private Integer id;
    private List<DungeonDto> dungeons;
    private Integer playerId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<DungeonDto> getDungeons() {
        return dungeons;
    }

    public void setDungeons(List<DungeonDto> dungeons) {
        this.dungeons = dungeons;
    }

    public Integer getPlayerId() {
        return playerId;
    }

    public void setPlayerId(Integer playerId) {
        this.playerId = playerId;
    }
}
