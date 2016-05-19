package com.ylitormatech.sensingworld.domain.repository.impl;

import com.ylitormatech.sensingworld.domain.entity.SensorEntity;
import com.ylitormatech.sensingworld.domain.repository.SensorRepository;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Marco Ylitörmä on 02/05/16.
 */
@Repository("sensorRepository")
@Transactional
public class SensorRepositoryImpl implements SensorRepository{

    @PersistenceContext
    EntityManager em;

    public void add(SensorEntity sensorEntity) {
        em.persist(sensorEntity);
    }

    public List<SensorEntity> findAll() {
        List<SensorEntity> list = em.createQuery("FROM SensorEntity").getResultList();
        return list;
    }

    public SensorEntity find(Integer id) {
        Query query = em.createQuery("FROM SensorEntity WHERE id=:id");
        query.setParameter("id", id);
        return (SensorEntity) query.getSingleResult();
    }

    public SensorEntity find(String name) {
        Query query = em.createQuery("FROM SensorEntity WHERE name=:name");
        query.setParameter("name", name);
        if (query.getFirstResult() == 0) {
            return null;
        }

        return (SensorEntity) query.getSingleResult();
    }

    public void update(SensorEntity sensorEntity) {
        em.merge(sensorEntity);
    }

    public void remove(Integer id) {
        Query query = em.createQuery("FROM SensorEntity WHERE id=:id");
        query.setParameter("id", id);
        em.remove((SensorEntity) query.getSingleResult());
    }

//    public SensorEntity find(Integer id) {
//        SensorEntity sensorEntity = null;
//        // by id, which should also be hashmap key but just to be sure iterate.
//        for (Map.Entry<Integer, SensorEntity> entry : sensors.entrySet()) {
//            if (entry.getValue().getId().intValue() == id.intValue()) {
//                sensorEntity = entry.getValue();
//                continue;
//            }
//        }
//        return sensorEntity;
//    }
//
//    public SensorEntity remove(Integer id) {
//        SensorEntity sensorEntity = null;
//        if (sensors.containsKey(id)) {
//            sensorEntity = sensors.remove(id);
//        }
//        return sensorEntity;
//    }
//

}
