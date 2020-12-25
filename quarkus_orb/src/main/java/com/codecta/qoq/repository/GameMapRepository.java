package com.codecta.qoq.repository;

import com.codecta.qoq.model.GameMap;

public class GameMapRepository extends Repository<GameMap, Integer> {
    public GameMapRepository () { super(GameMap.class); }
}
