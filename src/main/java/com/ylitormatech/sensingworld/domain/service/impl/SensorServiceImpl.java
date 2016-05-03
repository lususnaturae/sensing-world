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

//    public List<SensorEntity> getSensors() {
//        return sensorRepository.findAll();
//    }
//
//    public SensorEntity get(Integer id) {
//        return sensorRepository.find(id);
//    }
//
//    public void update(SensorEntity s) {
//        sensorRepository.update(s);
//    }
//
//    public SensorEntity remove(SensorEntity s) {
//        return sensorRepository.remove(s.getId());
//    }

    public SensorEntity create(String name, List<String> usage) {

        SensorEntity sensorEntity = new SensorEntity();
        sensorEntity.setName(name);
        //sensorEntity.setUsage(usage);
        sensorRepository.add(sensorEntity);
        return sensorEntity;
    }
}
