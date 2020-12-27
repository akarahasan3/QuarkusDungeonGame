package com.codecta.qoq.repository;

import com.codecta.qoq.model.Dungeon;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;

@ApplicationScoped
@Transactional(Transactional.TxType.MANDATORY)
public class DungeonRepository extends Repository<Dungeon, Integer> {
    public DungeonRepository() {
        super(Dungeon.class);
    }
}