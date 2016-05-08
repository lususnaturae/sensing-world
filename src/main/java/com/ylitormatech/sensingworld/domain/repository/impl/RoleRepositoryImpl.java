package com.ylitormatech.sensingworld.domain.repository.impl;

import com.ylitormatech.sensingworld.domain.entity.RoleEntity;
import com.ylitormatech.sensingworld.domain.repository.RoleRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;


/**
 * Created by marco on 7.5.2016.
 */
@Repository("roleRepository")
public class RoleRepositoryImpl implements RoleRepository{

    @PersistenceContext
    EntityManager em;

    @Override
    public RoleEntity find(Integer id) {
        return em.find(RoleEntity.class, id);
    }

    @Override
    public RoleEntity find(String name) {
        return em.createQuery("FROM RoleEntity u WHERE u.name=:name", RoleEntity.class).setParameter("name",name).getSingleResult();
    }

    @Override
    public List<RoleEntity> findAll() {
        return (List<RoleEntity>)em.createQuery("FROM RoleEntity u").getResultList();
    }

    @Override
    public void add(RoleEntity r) {
        em.persist(r);
    }

    @Override
    public void update(RoleEntity r) {
        em.merge(r);
    }

    @Override
    public void remove(Integer id) {
        Query query = em.createQuery("FROM RoleEntity WHERE id=:id");
        query.setParameter("id", id);
        em.remove(query.getSingleResult());
    }

    @Override
    public void remove(RoleEntity r) {
       em.remove(r);
    }
}
