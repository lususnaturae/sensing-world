package com.ylitormatech.sensingworld.domain.repository.impl;

import com.ylitormatech.sensingworld.domain.entity.SensorEntity;
import com.ylitormatech.sensingworld.domain.repository.SensorRepository;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.HashMap;

/**
 * Created by Marco Ylitörmä on 02/05/16.
 */
@Repository("sensorRepository")
@Transactional
public class SensorRepositoryImpl implements SensorRepository{

    @PersistenceContext
    EntityManager em;

    // private HashMap<Integer, SensorEntity> sensors = new HashMap<Integer, SensorEntity>();

    //private int counter = 0;

//    @PostConstruct
//    public void initDummyData() {
//        sensors.put(1,new SensorEntity(1, "Lämpönturi 1", "TEMPERATURE"));
//        sensors.put(2,new SensorEntity(2, "Lämpönturi 2", "TEMPERATURE"));
//        sensors.put(3,new SensorEntity(3, "Nopeus 1", "SPEED"));
//        sensors.put(4,new SensorEntity(4, "Nopeus 2", "SPEED"));
//        counter = 4;
//    }

    //public int getCounter() {return counter;}

    public void add(SensorEntity sensorEntity) {
        em.persist(sensorEntity);
    }

//    public void update(SensorEntity sensorEntity) {
//        // quotes are compared by their id
//        for (Map.Entry<Integer, SensorEntity> entry : sensors.entrySet()) {
//            if (entry.getValue().getId().intValue() == sensorEntity.getId().intValue()) {
//                sensors.put(entry.getKey(),sensorEntity);
//                continue;
//            }
//        }
//
//    }
//
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
//    public List<SensorEntity> findAll() {
//        // hashmap to list, is this necessary?
//        List<SensorEntity> list = new ArrayList<SensorEntity>(sensors.values());
//        return list;
//    }
}
