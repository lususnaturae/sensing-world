package com.ylitormatech.sensingworld.domain.repository.impl;

import com.ylitormatech.sensingworld.domain.entity.SensorEntity;
import com.ylitormatech.sensingworld.domain.repository.SensorRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Marco Ylitörmä on 02/05/16.
 */
@Repository("sensorRepository")
@Transactional
public class SensorRepositoryImpl implements SensorRepository{

    @PersistenceContext
    EntityManager em;

    @Override
    public void add(SensorEntity sensorEntity) {
        em.persist(sensorEntity);
    }

    @Override
    public List<SensorEntity> findAll() {
        List<SensorEntity> list = em.createQuery("FROM SensorEntity").getResultList();
        return list;
    }

    @Override
    public SensorEntity find(Integer id) {
        Query query = em.createQuery("FROM SensorEntity WHERE id=:id");
        query.setParameter("id", id);
        return (SensorEntity) query.getSingleResult();
    }

    @Override
    public void update(SensorEntity sensorEntity) {
        em.merge(sensorEntity);
    }

    @Override
    public void remove(Integer id) {
        Query query = em.createQuery("FROM SensorEntity WHERE id=:id");
        query.setParameter("id", id);
        em.remove(query.getSingleResult());
    }

    @Override
    public void remove(SensorEntity r) {
        em.remove(r);
    }

}
