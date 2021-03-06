package com.ylitormatech.sensingworld.domain.entity;

import javax.persistence.*;
import java.util.List;

/**
 * Created by marco on 6.5.2016.
 */
@Table
@Entity
public class UserEntity {

    @Id
    @GeneratedValue
    Integer id;

    String email;

    String password;

    String role;

    String username;

    @OneToMany
    List<SensorEntity> sensors;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<SensorEntity> getSensors() {
        return sensors;
    }

    public void setSensors(List<SensorEntity> sensors) {
        this.sensors = sensors;
    }
}
