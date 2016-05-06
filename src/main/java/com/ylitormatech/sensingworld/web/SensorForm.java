package com.ylitormatech.sensingworld.web;

import org.springframework.core.style.ToStringCreator;

import java.util.List;

/**
 * Created by Marco Ylitörmä on 03/05/16.
 */
public class SensorForm {

    Integer id;
    String name;
    String usagetoken;

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

    public String getUsagetoken() {
        return usagetoken;
    }

    public void setUsagetoken(String usagetoken) {
        this.usagetoken = usagetoken;
    }


}
