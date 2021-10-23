package com.foop.delivery.domain.service;

import com.foop.delivery.domain.model.Restaurants;
import org.springframework.stereotype.Component;

@Component
public interface RegisterRestaurantService {
    Restaurants save(Restaurants restaurants);
}
