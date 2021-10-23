package com.foop.delivery.infrastructure.repository;

import com.foop.delivery.domain.model.City;
import com.foop.delivery.domain.model.State;
import com.foop.delivery.domain.repository.CityRepository;
import com.foop.delivery.domain.repository.StateRepository;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Component
public class CityRepositoryImpl implements CityRepository {

    @PersistenceContext
    private EntityManager manager;

    @Override
    public List<City> list() {
        return manager.createQuery("from City", City.class).getResultList();
    }

    @Override
    public City byId(Long id) {
        return manager.find(City.class, id);
    }

    @Override
    @Transactional
    public City save(City city) {
        return manager.merge(city);
    }

    @Override
    @Transactional
    public void remove(Long id) {
        City city = byId(id);
        if(city == null) {
            throw new EmptyResultDataAccessException(1);
        }
        manager.remove(city);
    }
}
