package com.ylitormatech.sensingworld.domain.entity;

import javax.persistence.*;
import java.util.List;


/**
 * Created by marco on 7.5.2016.
 */
@Entity
@Table(name = "roleentity")
public class RoleEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String name;

    @ManyToOne
    private List<UserEntity> users;

    public RoleEntity() {
    }

    public RoleEntity(String name) {
        this.name = name;
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

    public List<UserEntity> getUsers() {
        return users;
    }

    public void setUsers(List<UserEntity> users) {
        this.users = users;
    }
}
