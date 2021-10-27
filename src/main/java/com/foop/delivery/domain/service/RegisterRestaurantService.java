package com.foop.delivery.domain.service;

import com.foop.delivery.domain.model.Restaurant;
import org.springframework.stereotype.Component;

@Component
public interface RegisterRestaurantService {
    Restaurant save(Restaurant restaurant);
}
