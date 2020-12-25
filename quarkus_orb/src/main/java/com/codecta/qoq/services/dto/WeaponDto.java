package com.codecta.qoq.services.dto;

import java.util.List;

public class WeaponDto {
    private Integer id;
    private String name;
    private Integer damage;
    private List<PlayerDto> players;
    private List<MonsterDto> monster;
    private Integer dungeonId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getDamage() {
        return damage;
    }

    public void setDamage(Integer damage) {
        this.damage = damage;
    }

    public List<PlayerDto> getPlayers() {
        return players;
    }

    public void setPlayers(List<PlayerDto> players) {
        this.players = players;
    }

    public List<MonsterDto> getMonster() {
        return monster;
    }

    public void setMonster(List<MonsterDto> monster) {
        this.monster = monster;
    }

    public Integer getDungeonId() {
        return dungeonId;
    }

    public void setDungeonId(Integer dungeonId) {
        this.dungeonId = dungeonId;
    }
}
