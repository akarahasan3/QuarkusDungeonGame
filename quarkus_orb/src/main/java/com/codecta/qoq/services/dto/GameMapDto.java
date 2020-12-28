package com.codecta.qoq.services.dto;

import com.codecta.qoq.model.Dungeon;

import java.util.List;

public class GameMapDto {
    private Integer id;
    private String story;
    private List<DungeonDto> dungeons;
    private Integer playerId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getStory() {
        return story;
    }

    public void setStory(String story) {
        this.story = story;
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
