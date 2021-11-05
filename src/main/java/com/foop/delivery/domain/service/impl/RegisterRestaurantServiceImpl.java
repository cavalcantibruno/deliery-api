package com.foop.delivery.domain.service.impl;

import com.foop.delivery.domain.exception.EntityNotFoundException;
import com.foop.delivery.domain.exception.RestaurantNotFoundException;
import com.foop.delivery.domain.model.Kitchen;
import com.foop.delivery.domain.model.Restaurant;
import com.foop.delivery.domain.repository.RestaurantRepository;
import com.foop.delivery.domain.service.RegisterKitchenService;
import com.foop.delivery.domain.service.RegisterRestaurantService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@AllArgsConstructor
@Service
public class RegisterRestaurantServiceImpl implements RegisterRestaurantService {

    public static final String NOT_FOUND = "Not exists a register Restaurant with id %d ";
    private final RestaurantRepository restaurantRepository;
    private final RegisterKitchenService kitchenService;

    @Override
    @Transactional
    public Restaurant save(Restaurant restaurant) {
        Long kitchenId = restaurant.getKitchen().getId();
        Kitchen kitchen = kitchenService.findById(kitchenId);
        restaurant.setKitchen(kitchen);
        return restaurantRepository.save(restaurant);
    }

    @Override
    public Restaurant findById(Long id) {
        return  restaurantRepository
                .findById(id).orElseThrow(() -> new RestaurantNotFoundException(id));
    }
}
