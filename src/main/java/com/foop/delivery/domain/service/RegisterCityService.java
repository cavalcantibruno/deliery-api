package com.foop.delivery.domain.service;

import com.foop.delivery.domain.model.City;
import org.springframework.stereotype.Component;

@Component
public interface RegisterCityService {
    City save(City city);
    void delete(Long id);
    City findById(Long id);
}
