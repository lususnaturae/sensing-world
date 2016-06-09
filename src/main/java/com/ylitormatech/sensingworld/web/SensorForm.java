package com.ylitormatech.sensingworld.web;

import com.ylitormatech.sensingworld.domain.entity.SensorEntity;
import com.ylitormatech.sensingworld.domain.entity.UserEntity;
import org.springframework.core.style.ToStringCreator;

import java.util.List;

/**
 * Created by Marco Ylitörmä on 03/05/16.
 */
public class SensorForm {

    Integer id;
    String name;
    String usagetoken;
    String apikey;
    Integer userid;

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

    public String getApikey() {
        return apikey;
    }

    public void setApikey(String apikey) {
        this.apikey = apikey;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }
}
