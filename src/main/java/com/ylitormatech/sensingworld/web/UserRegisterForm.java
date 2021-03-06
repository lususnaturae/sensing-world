package com.ylitormatech.sensingworld.web;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * Created by marco on 6.5.2016.
 */
public class UserRegisterForm {

    @NotEmpty
    String username;

    @NotEmpty@Email
    String email;

    @NotEmpty
    String password;

    @NotEmpty
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

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
