package com.ylitormatech.sensingworld.domain.service.impl;

import com.ylitormatech.sensingworld.domain.entity.RoleEntity;
import com.ylitormatech.sensingworld.domain.repository.RoleRepository;
import com.ylitormatech.sensingworld.domain.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Marco Ylitörmä on 07/05/16.
 */
@Service("roleService")
public class RoleServiceImpl implements RoleService{


    @Autowired
    RoleRepository roleRepository;

    @Override
    public List<RoleEntity> findAll() {
        return roleRepository.findAll();
    }

    @Override
    public RoleEntity find(Integer id) {
        return roleRepository.find(id);
    }

    @Override
    public void update(RoleEntity r) {
        roleRepository.update(r);
    }

    @Override
    public RoleEntity add(String name) {
        RoleEntity roleEntity = new RoleEntity();
        roleEntity.setName(name);
        roleRepository.add(roleEntity);
        return roleEntity;
    }
}
