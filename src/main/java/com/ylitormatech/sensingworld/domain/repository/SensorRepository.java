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

    public List<SensorEntity> findAll();

    public SensorEntity find(Integer id);


//    public void update(SensorEntity sensorEntity);
//

//    public SensorEntity remove(Integer id);
//


}
