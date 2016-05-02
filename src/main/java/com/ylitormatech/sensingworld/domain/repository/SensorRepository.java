package com.ylitormatech.sensingworld.domain.repository;

import com.ylitormatech.sensingworld.domain.entity.Sensor;

import java.util.List;

/**
 * Created by Marco Ylitörmä on 02/05/16.
 */
public interface SensorRepository {

    // just to create unique ids
    public int getCounter();

    public void add(Sensor sensor);

    public void update(Sensor sensor);

    public Sensor find(Integer id);

    public Sensor remove(Integer id);

    public List<Sensor> findAll();

}
