package com.codecta.qoq.repository;

import com.codecta.qoq.model.Monster;

public class MonsterRepository extends Repository<Monster, Integer> {
    public MonsterRepository(){ super(Monster.class); }
}
