package com.ylitormatech.sensingworld.domain.repository.impl;

import com.ylitormatech.sensingworld.domain.entity.UserEntity;
import com.ylitormatech.sensingworld.domain.repository.UserRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * Created by marco on 6.5.2016.
 */
@Repository
public class UserRepositoryImpl implements UserRepository{

    @PersistenceContext
    EntityManager em;

    @Override
    public UserEntity find(Integer id) {
        return em.find(UserEntity.class, id);
    }

    @Override
    public UserEntity find(String username) {
        return em.createQuery("FROM UserEntity u WHERE u.username=:username", UserEntity.class).setParameter("username",username).getSingleResult();
    }

    @Override
    public List<UserEntity> findAll() {
        return (List<UserEntity>)em.createQuery("FROM UserEntity u").getResultList();
    }

    @Override
    public void add(UserEntity u) {
        em.persist(u);
    }

    @Override
    public void update(UserEntity u) {
        em.merge(u);
    }


}
