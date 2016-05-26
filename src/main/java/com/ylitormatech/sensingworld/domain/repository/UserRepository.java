package com.ylitormatech.sensingworld.domain.repository;

import com.ylitormatech.sensingworld.domain.entity.UserEntity;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;

/**
 * Created by marco on 6.5.2016.
 */
public interface UserRepository {

    public UserEntity getUser(Integer id);

    public UserEntity getUser(String username);

    public boolean getUserSanityCheck(String username);

    public boolean getUserSanityCheck(Integer id);

    public List<UserEntity> getUsers();

    public void store(UserEntity u);

    public void update(UserEntity u);

    public void remove(Integer id);
}
