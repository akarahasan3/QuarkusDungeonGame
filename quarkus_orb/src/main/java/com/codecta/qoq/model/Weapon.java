package com.codecta.qoq.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(schema = "quarkusorb", name = "WEAPON")
public class Weapon extends ModelObject {
    @SequenceGenerator(
            name = "WeaponSeq",
            sequenceName = "WEAPON_SEQ",
            schema = "quarkusorb",
            allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "WeaponSeq")
    @Id
    @Column(name = "ID", nullable = false)
    private Integer id;

    private String name;
    private Integer damage;

    @OneToMany(mappedBy = "weapon", fetch = FetchType.LAZY)
    private List<Player> players;

    @OneToMany(mappedBy = "weapon", fetch = FetchType.LAZY)
    private List<Monster> monster;

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

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    public List<Monster> getMonster() {
        return monster;
    }

    public void setMonster(List<Monster> monster) {
        this.monster = monster;
    }

    @Override
    public Integer getId() { return this.id; }
}
