package com.codecta.qoq.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(schema = "quarkusorb", name = "MONSTER")
public class Monster extends ModelObject {
    @SequenceGenerator(
            name = "MonsterSeq",
            sequenceName = "MONSTER_SEQ",
            schema = "quarkusorb",
            allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "MonsterSeq")
    @Id
    @Column(name = "ID", nullable = false)
    private Integer id;

    private String name;
    private Integer health;
    private Integer damage;

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

    public Weapon getWeapon() {
        return weapon;
    }

    public void setWeapon(Weapon weapon) {
        this.weapon = weapon;
    }

    @Override
    public Integer getId() { return this.id; }
}
