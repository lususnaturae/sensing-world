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

    public UserEntity getUser(Integer id) {
        return em.find(UserEntity.class, id);
    }

    public UserEntity getUser(String username) {
            return em.createQuery("FROM UserEntity u WHERE u.username=:username", UserEntity.class).setParameter("username", username).getSingleResult();
    }

    public boolean getUserSanityCheck(String username){
        List<UserEntity> list = em.createQuery("FROM UserEntity u WHERE u.username=:username", UserEntity.class).setParameter("username",username).getResultList();
        if(!list.isEmpty())
        {
            return false;
        }
        return true;
    }

    public List<UserEntity> getUsers() {
        return (List<UserEntity>)em.createQuery("FROM UserEntity u").getResultList();
    }

    public void store(UserEntity u) {
        em.persist(u);
    }

    public void update(UserEntity u) {
        em.merge(u);
    }
}
