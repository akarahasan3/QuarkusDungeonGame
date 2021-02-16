package com.codecta.qoq.repository;

import com.codecta.qoq.model.GameMap;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.List;

@ApplicationScoped
@Transactional(Transactional.TxType.MANDATORY)
public class GameMapRepository extends Repository<GameMap, Integer> {
    public GameMapRepository () { super(GameMap.class); }

//    @Override
//    public GameMap findById(Integer id) {
//        Query query = entityManager.createQuery("SELECT g FROM GameMap g JOIN g.dungeons d WHERE g.id = :id ORDER BY d.id ASC");// ORDER BY d.dungeonOrder
//        query.setParameter("id", id);
//        List result = query.getResultList();
//        return (GameMap) result.get(0);
//    }
}
