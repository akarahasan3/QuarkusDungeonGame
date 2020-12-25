package com.codecta.qoq.model;

import javax.persistence.*;

public class Dungeon extends ModelObject {
    @SequenceGenerator(
            name = "DungeonSeq",
            sequenceName = "DUNGEON_SEQ",
            schema = "quarkusorb",
            allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "DungeonSeq")
    @Id
    @Column(name = "ID", nullable = false)
    private Integer id;

    @ManyToOne
    private GameMap map;

    @OneToOne
    private Monster monster;

    @OneToOne
    private Weapon weapon;

    @Override
    public Integer getId() {
        return this.id;
    }

    public GameMap getMap() {
        return map;
    }

    public void setMap(GameMap map) {
        this.map = map;
    }

    public Monster getMonster() {
        return monster;
    }

    public void setMonster(Monster monster) {
        this.monster = monster;
    }

    public Weapon getWeapon() {
        return weapon;
    }

    public void setWeapon(Weapon weapon) {
        this.weapon = weapon;
    }
}
