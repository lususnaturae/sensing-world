package com.ylitormatech.sensingworld.domain.service.impl;

import com.ylitormatech.sensingworld.domain.entity.Sensor;
import com.ylitormatech.sensingworld.domain.repository.SensorRepository;
import com.ylitormatech.sensingworld.domain.service.SensorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Marco Ylitörmä on 02/05/16.
 */
@Service
public class SensorServiceImpl implements SensorService{

    @Autowired
    SensorRepository sensorsRepository;

    public List<Sensor> getSensors() {
        return sensorsRepository.findAll();
    }

    public Sensor get(Integer id) {
        return sensorsRepository.find(id);
    }

    public void update(Sensor s) {
        sensorsRepository.update(s);
    }

    public Sensor remove(Sensor s) {
        return sensorsRepository.remove(s.getId());
    }

    public void create(Sensor s) {
        // STUPID WAY TO CREATE IDs. BUT THIS IS JUST DEMO CODE.
        s.setId(sensorsRepository.getCounter() +1);
        sensorsRepository.add(s);
    }
}
