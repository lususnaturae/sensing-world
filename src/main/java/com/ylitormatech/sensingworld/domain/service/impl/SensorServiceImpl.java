package com.ylitormatech.sensingworld.domain.service.impl;

import com.ylitormatech.sensingworld.domain.entity.SensorEntity;
import com.ylitormatech.sensingworld.domain.repository.SensorRepository;
import com.ylitormatech.sensingworld.domain.service.SensorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Marco Ylitörmä on 02/05/16.
 */
@Service("sensorService")
public class SensorServiceImpl implements SensorService{

    @Autowired
    SensorRepository sensorRepository;

    @Override
    public List<SensorEntity> findAll() {
        return sensorRepository.findAll();
    }

    @Override
    public SensorEntity find(Integer id) {
        return sensorRepository.find(id);
    }

    @Override
    public void update(SensorEntity s) {
        sensorRepository.update(s);
    }

    @Override
    public void remove(Integer id) {
        sensorRepository.remove(id);
    }

    @Override
    public SensorEntity add(String name, String usage) {
        SensorEntity sensorEntity = new SensorEntity();
        sensorEntity.setName(name);
        sensorEntity.setUsagetoken(usage);
        sensorRepository.add(sensorEntity);
        return sensorEntity;
    }
}
