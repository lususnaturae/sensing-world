package com.ylitormatech.sensingworld.domain.entity;


import org.springframework.core.style.ToStringCreator;

import javax.persistence.*;

/**
 * Created by Marco Ylitörmä on 02/05/16.
 */
@Table(name = "sensors")
@Entity
public class SensorEntity {


    @Id
    @GeneratedValue
    Integer id;

    @Column(length = 40)
    String name;

    @Column(length = 40)
    String usagetoken;

    public SensorEntity() {
    }

    public SensorEntity(String name, String usagetoken) {
        this.name = name;
        this.usagetoken = usagetoken;
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

    public String getUsagetoken() {
        return usagetoken;
    }

    public void setUsagetoken(String usagetoken) {
        this.usagetoken = usagetoken;
    }

    public boolean isNew() {
        return (this.id == null);
    }

    @Override
    public String toString() {
        return new ToStringCreator(this)

                .append("id", this.getId())
                .append("new", this.isNew())
                .append("name", this.getName())
                .append("usagetokem", this.getUsagetoken())
                .toString();
    }
}
