package com.ylitormatech.sensingworld.domain.repository;

import com.ylitormatech.sensingworld.domain.entity.SensorEntity;

import java.util.List;

/**
 * Created by Marco Ylitörmä on 02/05/16.
 */
public interface SensorRepository {

    // just to create unique ids
    //public int getCounter();

    void add(SensorEntity sensorEntity);

    List<SensorEntity> findAll();

    SensorEntity find(Integer id);

    SensorEntity find(String name);

    void update(SensorEntity sensorEntity);

    void remove(Integer id);



}
