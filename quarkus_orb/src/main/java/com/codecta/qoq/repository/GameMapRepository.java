package com.codecta.qoq.repository;

import com.codecta.qoq.model.GameMap;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;

@ApplicationScoped
@Transactional(Transactional.TxType.MANDATORY)
public class GameMapRepository extends Repository<GameMap, Integer> {
    public GameMapRepository () { super(GameMap.class); }
}
