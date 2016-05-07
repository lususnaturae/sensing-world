package com.ylitormatech.sensingworld.domain.entity;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by marco on 7.5.2016.
 */
@Entity
@Table(name = "role")
public class RoleEntity {
    private Integer id;
    private String name;
    private Set<UserEntity> users;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
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

    @ManyToMany(mappedBy = "users")
    public Set<UserEntity> getUsers() {
        return users;
    }

    public void setUsers(Set<UserEntity> users) {
        this.users = users;
    }
}
