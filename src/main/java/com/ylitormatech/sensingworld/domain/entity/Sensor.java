package com.ylitormatech.sensingworld.domain.entity;


import java.util.List;

/**
 * Created by Marco Ylitörmä on 02/05/16.
 */
public class Sensor {

    Integer id;


    String name;

    List<String> usage;

    public Sensor() {
    }

    public Sensor(Integer id, String name, List<String> usage) {
        this.id = id;
        this.name = name;
        this.usage = usage;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getUsage() {
        return usage;
    }

    public void setUsage(List<String> usage) {
        this.usage = usage;
    }

}
