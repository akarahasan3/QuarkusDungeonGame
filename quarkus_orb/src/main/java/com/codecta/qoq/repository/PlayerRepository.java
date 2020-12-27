package com.codecta.qoq.repository;

import com.codecta.qoq.model.Player;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;

@ApplicationScoped
@Transactional(Transactional.TxType.MANDATORY)
public class PlayerRepository extends Repository<Player, Integer> {
    public PlayerRepository() { super(Player.class); }
}
