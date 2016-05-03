package com.ylitormatech.sensingworld.web;

import java.util.List;

/**
 * Created by Marco Ylitörmä on 03/05/16.
 */
public class SensorForm {

    String name;
    List<String> usage;

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
