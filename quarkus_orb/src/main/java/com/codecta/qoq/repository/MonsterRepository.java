package com.codecta.qoq.repository;

import com.codecta.qoq.model.Monster;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;

@ApplicationScoped
@Transactional(Transactional.TxType.MANDATORY)
public class MonsterRepository extends Repository<Monster, Integer> {
    public MonsterRepository(){ super(Monster.class); }
}
