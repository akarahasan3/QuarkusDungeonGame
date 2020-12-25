package com.codecta.qoq.repository;

import com.codecta.qoq.model.Dungeon;

public class DungeonRepository extends Repository<Dungeon, Integer> {
    public DungeonRepository() {
        super(Dungeon.class);
    }
}