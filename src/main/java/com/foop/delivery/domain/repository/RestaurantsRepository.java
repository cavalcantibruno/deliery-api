package com.foop.delivery.domain.repository;

import com.foop.delivery.domain.model.Restaurants;

import java.util.List;

public interface RestaurantsRepository {
    List<Restaurants> list();
    Restaurants byId(Long id);
    Restaurants save(Restaurants restaurants);
    void remove(Restaurants restaurants);
}
