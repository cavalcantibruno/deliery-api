package com.foop.delivery.domain.service;

import com.foop.delivery.domain.model.Restaurant;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public interface RegisterRestaurantService {
    Restaurant save(Restaurant restaurant);
    Restaurant findById(Long id);
}
