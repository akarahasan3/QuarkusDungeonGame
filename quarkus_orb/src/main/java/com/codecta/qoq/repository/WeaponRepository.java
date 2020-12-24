package com.codecta.qoq.repository;

import com.codecta.qoq.model.Weapon;

public class WeaponRepository extends Repository<Weapon, Integer> {
    public WeaponRepository() { super(Weapon.class); }
}
