package com.ylitormatech.sensingworld.domain.service;


import com.ylitormatech.sensingworld.domain.entity.RoleEntity;

import java.util.List;

/**
 * Created by Marco Ylitörmä on 07/05/16.
 */
public interface RoleService {
    public List<RoleEntity> findAll();

    public RoleEntity find(Integer id);

    public void update(RoleEntity r);



    public RoleEntity create(String name);
}
