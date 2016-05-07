package com.ylitormatech.sensingworld.web;

import com.ylitormatech.sensingworld.domain.service.SensorService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by marco on 6.5.2016.
 */
public class UserRegisterForm {

    @Autowired
    SensorService sensorService;

    String username;
    String email;
    String password;
    String role;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

    public Set<String> getRoles() {
        Set<String> roles = new HashSet<>();
        roles.add(role);
        return roles;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
