package com.ylitormatech.sensingworld.domain.repository;

import com.ylitormatech.sensingworld.domain.entity.RoleEntity;
import com.ylitormatech.sensingworld.domain.entity.UserEntity;

import javax.management.relation.Role;
import java.util.List;
import java.util.Set;

/**
 * Created by marco on 7.5.2016.
 */
public interface RoleRepository {

    public RoleEntity find(Integer id);

    public RoleEntity find(String name);

    public List<RoleEntity> findAll();

    public void add(RoleEntity r);

    public void update(RoleEntity r);

    public void remove(Integer i);
    public void remove(RoleEntity r);
}
