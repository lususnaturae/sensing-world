package com.ylitormatech.sensingworld.domain.repository.impl;

import com.ylitormatech.sensingworld.domain.entity.RoleEntity;
import com.ylitormatech.sensingworld.domain.entity.UserEntity;
import com.ylitormatech.sensingworld.domain.repository.RoleRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * Created by marco on 7.5.2016.
 */
@Repository("roleRepository")
public class RoleRepositoryImpl implements RoleRepository{

    @PersistenceContext
    EntityManager em;

    @Override
    public RoleEntity getRole(Integer id) {
        return em.find(RoleEntity.class, id);
    }

    @Override
    public RoleEntity getRole(String name) {
        return em.createQuery("FROM RoleEntity u WHERE u.name=:name", RoleEntity.class).setParameter("name",name).getSingleResult();
    }

    @Override
    public List<RoleEntity> getRoles() {
        return (List<RoleEntity>)em.createQuery("FROM RoleEntity u").getResultList();
    }

    @Override
    public void store(RoleEntity r) {
        em.persist(r);
    }

    @Override
    public void update(RoleEntity r) {
        em.merge(r);
    }
}
