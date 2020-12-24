package com.codecta.qoq.repository;

import com.codecta.qoq.model.Player;

public class PlayerRepository extends Repository<Player, Integer> {
    public PlayerRepository() { super(Player.class); }
}
