package com.codecta.qoq.services.dto;

public class DungeonDto {
    private Integer id;
    private Integer healing_potion;
    private Integer mapId;
    private Integer monsterId;
    private Integer weaponId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getHealing_potion() {
        return healing_potion;
    }

    public void setHealing_potion(Integer healing_potion) {
        this.healing_potion = healing_potion;
    }

    public Integer getMapId() {
        return mapId;
    }

    public void setMapId(Integer mapId) {
        this.mapId = mapId;
    }

    public Integer getMonsterId() {
        return monsterId;
    }

    public void setMonsterId(Integer monsterId) {
        this.monsterId = monsterId;
    }

    public Integer getWeaponId() {
        return weaponId;
    }

    public void setWeaponId(Integer weaponId) {
        this.weaponId = weaponId;
    }


}
