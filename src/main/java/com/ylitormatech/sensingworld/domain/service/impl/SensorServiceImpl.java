package com.ylitormatech.sensingworld.domain.service.impl;

import com.ylitormatech.sensingworld.domain.entity.SensorEntity;
import com.ylitormatech.sensingworld.domain.entity.UserEntity;
import com.ylitormatech.sensingworld.domain.repository.SensorRepository;
import com.ylitormatech.sensingworld.domain.repository.UserRepository;
import com.ylitormatech.sensingworld.domain.service.SensorService;

import com.ylitormatech.sensingworld.security.ApiKeyGenerator;
import com.ylitormatech.sensingworld.web.WwwUser;
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

    @Autowired
    UserRepository userRepository;

    public List<SensorEntity> findAll() {
        return sensorRepository.findAll();
    }

    public List<SensorEntity> findMySensors(Integer id) {
        return sensorRepository.findMySensors(id);
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

    public SensorEntity findMySensor(Integer id, Integer userid) {
        return sensorRepository.findMySensor(id, userid);
    }

    public void update(SensorEntity s) {
        sensorRepository.update(s);
    }

    public void remove(Integer id) {
        sensorRepository.remove(id);
    }

    public void removeMySensor(Integer id, Integer userid) {
        sensorRepository.removeMySensor(id, userid);
    }

    public SensorEntity add(String name, String usage, WwwUser u) {

        SensorEntity sensorEntity = new SensorEntity();
        sensorEntity.setName(name);
        sensorEntity.setUsagetoken(usage);
        sensorEntity.setUser(userRepository.getUser(u.getId()));
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
