package com.codecta.qoq.services.dto;


public class PlayerDto {
    private Integer id;
    private String name;
    private Integer health;
    private Integer damage;
    private Integer healing_potion;
    private Integer weaponId;
    private Integer mapId;

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

    public Integer getHealth() {
        return health;
    }

    public void setHealth(Integer health) {
        this.health = health;
    }

    public Integer getDamage() {
        return damage;
    }

    public void setDamage(Integer damage) {
        this.damage = damage;
    }

    public Integer getHealing_potion() {
        return healing_potion;
    }

    public void setHealing_potion(Integer healing_potion) {
        this.healing_potion = healing_potion;
    }

    public Integer getWeaponId() {
        return weaponId;
    }

    public void setWeaponId(Integer weaponId) {
        this.weaponId = weaponId;
    }

    public Integer getMapId() {
        return mapId;
    }

    public void setMapId(Integer mapId) {
        this.mapId = mapId;
    }
}
