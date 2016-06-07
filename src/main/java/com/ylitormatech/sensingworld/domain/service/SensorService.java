package com.ylitormatech.sensingworld.domain.service;

import com.ylitormatech.sensingworld.domain.entity.SensorEntity;
import com.ylitormatech.sensingworld.domain.entity.UserEntity;
import com.ylitormatech.sensingworld.web.WwwUser;

import java.util.List;

/**
 * Created by Marco Ylitörmä on 02/05/16.
 */
public interface SensorService {
    public List<SensorEntity> findAll();

    public List<SensorEntity> findMySensors(Integer Id);

    public List<String> findAllApiKeys();

    public SensorEntity find(Integer id);

    public SensorEntity findMySensor(Integer id, Integer userid);

    public SensorEntity find(String name);

    public void update(SensorEntity q);

    public void remove(Integer id);

    public void removeMySensor(Integer id, Integer userid);

    public SensorEntity add(String name, String usage, WwwUser user);

    public SensorEntity add(SensorEntity sensorEntity);

}
