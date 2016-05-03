package com.ylitormatech.sensingworld.domain.entity;


import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.List;

/**
 * Created by Marco Ylitörmä on 02/05/16.
 */
@Table(name = "sensor")
@Entity
public class SensorEntity {


    @Id
    @GeneratedValue
    Integer id;

    @Column(length = 50)
    String name;


    //List<String> usage;

    public SensorEntity() {
    }

    public SensorEntity(Integer id, String name, List<String> usage) {
        this.id = id;
        this.name = name;
        //this.usage = usage;
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

//    public List<String> getUsage() {
//        return usage;
//    }
//
//    public void setUsage(List<String> usage) {
//        this.usage = usage;
//    }

}
