package com.ylitormatech.sensingworld.domain.service;

import com.ylitormatech.sensingworld.domain.entity.SensorEntity;

import java.util.List;

/**
 * Created by Marco Ylitörmä on 02/05/16.
 */
public interface SensorService {
//    public List<SensorEntity> getSensors();
//
//    public SensorEntity get(Integer id);
//
//    public void update(SensorEntity q);
//
//    public SensorEntity remove(SensorEntity q);

    public SensorEntity create(String name, List<String> usage);
}
