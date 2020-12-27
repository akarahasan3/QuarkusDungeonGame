package com.codecta.qoq.repository;

import com.codecta.qoq.model.Weapon;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;

@ApplicationScoped
@Transactional(Transactional.TxType.MANDATORY)
public class WeaponRepository extends Repository<Weapon, Integer> {
    public WeaponRepository() { super(Weapon.class); }
}
