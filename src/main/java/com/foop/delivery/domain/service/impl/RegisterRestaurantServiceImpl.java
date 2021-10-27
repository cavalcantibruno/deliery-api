package com.foop.delivery.domain.service.impl;

import com.foop.delivery.domain.model.Kitchen;
import com.foop.delivery.domain.model.Restaurant;
import com.foop.delivery.domain.repository.KitchenRepository;
import com.foop.delivery.domain.repository.RestaurantRepository;
import com.foop.delivery.domain.service.RegisterRestaurantService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;

@AllArgsConstructor
@Service
public class RegisterRestaurantServiceImpl implements RegisterRestaurantService {

    private final RestaurantRepository restaurantRepository;
    private final KitchenRepository kitchenRepository;

    @Override
    @Transactional
    public Restaurant save(Restaurant restaurant) {
        Long kitchenId = restaurant.getKitchen().getId();
        Kitchen kitchen = kitchenRepository
                .findById(kitchenId).orElseThrow(() -> new EntityNotFoundException("Not found"));
        if (kitchen == null) {
            throw new EntityNotFoundException(
                    String.format("Not exists register kitchen with code %d", kitchenId));
        }

        restaurant.setKitchen(kitchen);
        return restaurantRepository.save(restaurant);
    }
}
