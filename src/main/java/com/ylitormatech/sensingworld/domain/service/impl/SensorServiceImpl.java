package com.ylitormatech.sensingworld.domain.service.impl;

import com.ylitormatech.sensingworld.domain.entity.SensorEntity;
import com.ylitormatech.sensingworld.domain.repository.SensorRepository;
import com.ylitormatech.sensingworld.domain.service.SensorService;
import com.ylitormatech.sensingworld.security.ApiKeyGenerator;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Marco Ylitörmä on 02/05/16.
 */
@Service("sensorService")
@Transactional
public class SensorServiceImpl implements SensorService{

    @Autowired
    SensorRepository sensorRepository;

    public List<SensorEntity> findAll() {
        return sensorRepository.findAll();
    }

    public List<String> findAllApiKeys() {
        List<SensorEntity> ss = sensorRepository.findAll();
        List<String> apikeys = new ArrayList<>();
        for (SensorEntity s : ss) {
            apikeys.add(s.getApikey());
        }
        return apikeys;
    }


    public SensorEntity find(Integer id) {
        return sensorRepository.find(id);
    }

    public void update(SensorEntity s) {
        sensorRepository.update(s);
    }

    public void remove(Integer id) {
        sensorRepository.remove(id);
    }

    public SensorEntity add(String name, String usage) {

        SensorEntity sensorEntity = new SensorEntity();
        sensorEntity.setName(name);
        sensorEntity.setUsagetoken(usage);
        sensorEntity.setApikey(new ApiKeyGenerator().createNewApiKey(name));
        sensorRepository.add(sensorEntity);
        return sensorEntity;
    }

    public SensorEntity add(SensorEntity sensorEntity) {
        sensorRepository.add(sensorEntity);
        return sensorEntity;
    }

    public SensorEntity find(String name) {
        return sensorRepository.find(name);
    }
}
