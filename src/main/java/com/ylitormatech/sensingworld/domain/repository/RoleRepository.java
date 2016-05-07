package com.ylitormatech.sensingworld.domain.repository;

import com.ylitormatech.sensingworld.domain.entity.RoleEntity;
import com.ylitormatech.sensingworld.domain.entity.UserEntity;

import javax.management.relation.Role;
import java.util.List;

/**
 * Created by marco on 7.5.2016.
 */
public interface RoleRepository {

    public RoleEntity getRole(Integer id);

    public RoleEntity getRole(String name);

    public List<RoleEntity> getRoles();

    public void store(RoleEntity r);

    public void update(RoleEntity r);
}
