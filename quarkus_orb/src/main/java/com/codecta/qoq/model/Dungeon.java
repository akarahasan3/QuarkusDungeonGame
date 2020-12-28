package com.codecta.qoq.model;

import javax.persistence.*;

@Entity
@Table(schema = "quarkusorb", name = "DUNGEON")
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

    private Integer healing_potion;

    @ManyToOne
    private GameMap map;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinTable(schema = "quarkusorb", name = "DUNG_MONSTER",
            joinColumns = { @JoinColumn(name = "DUNGEON_ID") },
            inverseJoinColumns = { @JoinColumn(name = "MONSTER_ID") })
    private Monster monster;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinTable(schema = "quarkusorb", name = "dung_weapon",
            joinColumns =
                    { @JoinColumn(name = "dungeon_id") },
            inverseJoinColumns =
                    { @JoinColumn(name = "weapon_id") })
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

    public Integer getHealing_potion() {
        return healing_potion;
    }

    public void setHealing_potion(Integer healing_potion) {
        this.healing_potion = healing_potion;
    }
}
