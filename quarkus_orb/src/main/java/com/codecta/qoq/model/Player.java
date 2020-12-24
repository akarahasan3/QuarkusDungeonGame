package com.codecta.qoq.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(schema = "quarkusorb", name = "PLAYER")
public class Player extends ModelObject{
    @SequenceGenerator(
            name = "PlayerSeq",
            sequenceName = "PLAYER_SEQ",
            schema = "quarkusorb",
            allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PlayerSeq")
    @Id
    @Column(name = "ID", nullable = false)
    private Integer id;

    private String name;
    private Integer health;
    private Integer damage;
    private Integer healing_potion;

    @ManyToOne
    private Weapon weapon;


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

    public Weapon getWeapon() {
        return weapon;
    }

    public void setWeapon(Weapon weapon) {
        this.weapon = weapon;
    }

    @Override
    public Integer getId() {return this.id;}
}
