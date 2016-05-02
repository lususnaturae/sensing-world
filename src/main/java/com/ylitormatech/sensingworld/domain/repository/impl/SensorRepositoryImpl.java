package com.ylitormatech.sensingworld.domain.repository.impl;

import com.ylitormatech.sensingworld.domain.entity.Sensor;
import com.ylitormatech.sensingworld.domain.repository.SensorRepository;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Marco Ylitörmä on 02/05/16.
 */
@Repository
public class SensorRepositoryImpl implements SensorRepository{

    private HashMap<Integer, Sensor> sensors = new HashMap<Integer, Sensor>();

    private int counter = 0;

    @PostConstruct
    public void initDummyData() {
//        sensors.put(1,new Sensor(1, "Lämpönturi 1", "TEMPERATURE"));
//        sensors.put(2,new Sensor(2, "Lämpönturi 2", "TEMPERATURE"));
//        sensors.put(3,new Sensor(3, "Nopeus 1", "SPEED"));
//        sensors.put(4,new Sensor(4, "Nopeus 2", "SPEED"));
        counter = 4;
    }

    public int getCounter() {return counter;}

    public void add(Sensor sensor) {
        counter++;
        sensors.put(sensor.getId(), sensor);
    }

    public void update(Sensor sensor) {
        // quotes are compared by their id
        for (Map.Entry<Integer, Sensor> entry : sensors.entrySet()) {
            if (entry.getValue().getId().intValue() == sensor.getId().intValue()) {
                sensors.put(entry.getKey(),sensor);
                continue;
            }
        }

    }

    public Sensor find(Integer id) {
        Sensor sensor = null;
        // by id, which should also be hashmap key but just to be sure iterate.
        for (Map.Entry<Integer, Sensor> entry : sensors.entrySet()) {
            if (entry.getValue().getId().intValue() == id.intValue()) {
                sensor = entry.getValue();
                continue;
            }
        }
        return sensor;
    }

    public Sensor remove(Integer id) {
        Sensor sensor = null;
        if (sensors.containsKey(id)) {
            sensor = sensors.remove(id);
        }
        return sensor;
    }

    public List<Sensor> findAll() {
        // hashmap to list, is this necessary?
        List<Sensor> list = new ArrayList<Sensor>(sensors.values());
        return list;
    }
}
