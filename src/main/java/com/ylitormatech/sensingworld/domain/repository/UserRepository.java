package com.ylitormatech.sensingworld.domain.repository;

import com.ylitormatech.sensingworld.domain.entity.UserEntity;

import java.util.List;

/**
 * Created by marco on 6.5.2016.
 */
public interface UserRepository {

    public UserEntity find(Integer id);

    public UserEntity find(String username);

    public List<UserEntity> findAll();

    public void add(UserEntity u);

    public void update(UserEntity u);
}
