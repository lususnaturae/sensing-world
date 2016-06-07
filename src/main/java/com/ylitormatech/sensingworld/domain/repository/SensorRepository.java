package com.ylitormatech.sensingworld.domain.repository;

import com.ylitormatech.sensingworld.domain.entity.SensorEntity;
import com.ylitormatech.sensingworld.domain.entity.UserEntity;

import java.util.List;

/**
 * Created by Marco Ylitörmä on 02/05/16.
 */
public interface SensorRepository {

    // just to create unique ids
    //public int getCounter();

    void add(SensorEntity sensorEntity);

    List<SensorEntity> findAll();

    List<SensorEntity> findMySensors(Integer id);

    SensorEntity findMySensor(Integer id, Integer userid);

    SensorEntity find(Integer id);

    SensorEntity find(String name);

    void update(SensorEntity sensorEntity);

    void remove(Integer id);
    void removeMySensor(Integer id, Integer userid);



}
