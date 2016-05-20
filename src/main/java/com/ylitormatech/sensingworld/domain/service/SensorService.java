package com.ylitormatech.sensingworld.domain.service;

import com.ylitormatech.sensingworld.domain.entity.SensorEntity;

import java.util.List;

/**
 * Created by Marco Ylitörmä on 02/05/16.
 */
public interface SensorService {
    public List<SensorEntity> findAll();

    public SensorEntity find(Integer id);

    public SensorEntity find(String name);

    public void update(SensorEntity q);

    public void remove(Integer id);

    public SensorEntity add(String name, String usage);

    public SensorEntity add(SensorEntity sensorEntity);

}
