package com.foop.delivery.infrastructure.repository;

import com.foop.delivery.domain.model.Kitchen;
import com.foop.delivery.domain.model.Restaurants;
import com.foop.delivery.domain.repository.KitchenRepository;
import com.foop.delivery.domain.repository.RestaurantsRepository;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Component
public class RestaurantsRepositoryImpl implements RestaurantsRepository {
    @PersistenceContext
    private EntityManager manager;

    @Override
    public List<Restaurants> list() {
        return manager.createQuery("from Restaurants", Restaurants.class).getResultList();
    }

    @Override
    public Restaurants byId(Long id) {
        return manager.find(Restaurants.class, id);
    }

    @Override
    public Restaurants save(Restaurants restaurants) {
        return manager.merge(restaurants);
    }

    @Override
    public void remove(Restaurants restaurants) {
        manager.remove(byId(restaurants.getId()));
    }
}
