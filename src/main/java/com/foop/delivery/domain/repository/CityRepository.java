package com.foop.delivery.domain.repository;

import com.foop.delivery.domain.model.City;

import java.util.List;

public interface CityRepository {
    List<City> list();
    City byId(Long id);
    City save(City city);
    void remove(Long id);
}
