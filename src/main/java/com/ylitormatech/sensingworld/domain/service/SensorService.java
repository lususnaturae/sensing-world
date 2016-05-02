package com.ylitormatech.sensingworld.domain.service;

import com.ylitormatech.sensingworld.domain.entity.Sensor;

import java.util.List;

/**
 * Created by Marco Ylitörmä on 02/05/16.
 */
public interface SensorService {
    public List<Sensor> getSensors();

    public Sensor get(Integer id);

    public void update(Sensor q);

    public Sensor remove(Sensor q);

    public void create(Sensor q);
}
