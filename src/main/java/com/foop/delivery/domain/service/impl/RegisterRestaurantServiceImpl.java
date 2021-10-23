package com.foop.delivery.domain.service.impl;

import com.foop.delivery.domain.model.Kitchen;
import com.foop.delivery.domain.model.Restaurants;
import com.foop.delivery.domain.repository.KitchenRepository;
import com.foop.delivery.domain.repository.RestaurantsRepository;
import com.foop.delivery.domain.service.RegisterRestaurantService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;

@AllArgsConstructor
@Service
public class RegisterRestaurantServiceImpl implements RegisterRestaurantService {

    private final RestaurantsRepository restaurantsRepository;
    private final KitchenRepository kitchenRepository;

    @Override
    @Transactional
    public Restaurants save(Restaurants restaurants) {
        Long kitchenId = restaurants.getKitchen().getId();
        Kitchen kitchen = kitchenRepository.byId(kitchenId);
        if (kitchen == null) {
            throw new EntityNotFoundException(
                    String.format("Not exists register kitchen with code %d", kitchenId));
        }

        restaurants.setKitchen(kitchen);
        return restaurantsRepository.save(restaurants);
    }
}
