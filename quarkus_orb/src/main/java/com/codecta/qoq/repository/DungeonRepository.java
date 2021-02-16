package com.codecta.qoq.repository;

import com.codecta.qoq.model.Dungeon;
import com.codecta.qoq.model.GameMap;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.List;

@ApplicationScoped
@Transactional(Transactional.TxType.MANDATORY)
public class DungeonRepository extends Repository<Dungeon, Integer> {
    public DungeonRepository() {
        super(Dungeon.class);
    }

//    @Override
//    public List<Dungeon> findAll() {
//        Query query = entityManager.createQuery("SELECT d FROM Dungeon d ORDER BY d.dungeonOrder ASC");
//        List resultList = query.getResultList();
//        return (List<Dungeon>) resultList;
//    }

    public Dungeon findByDungeonOrder(Integer dungeonOrder) {
        Query query = entityManager.createQuery("SELECT * FROM Dungeon where dungeonorder like :dungeonOrder");
        query.setParameter("dungeonOrder", dungeonOrder + '%');
        Object dungeon = query.getSingleResult();
        return (Dungeon) dungeon;
    }
}