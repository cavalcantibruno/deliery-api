package com.foop.delivery.api.controller;

import com.foop.delivery.domain.model.Restaurants;
import com.foop.delivery.domain.repository.RestaurantsRepository;
import com.foop.delivery.domain.service.RegisterRestaurantService;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/restaurants")
public class RestaurantController {

    private final RestaurantsRepository restaurantsRepository;
    private final RegisterRestaurantService restaurantService;

    @GetMapping
    public List<Restaurants> list() {
        return restaurantsRepository.list();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Restaurants> byId(@PathVariable Long id) {
        Restaurants restaurant = restaurantsRepository.byId(id);
        if(restaurant != null) {
            return ResponseEntity.ok(restaurant);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody Restaurants restaurants) {
        try {
            restaurants = restaurantService.save(restaurants);
            return ResponseEntity.status(HttpStatus.CREATED).body(restaurants);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody Restaurants restaurants) {
        try {
            Restaurants restaurantsCurrent = restaurantsRepository.byId(id);
            if (restaurantsCurrent != null) {
                BeanUtils.copyProperties(restaurants, restaurantsCurrent, "id");
                restaurantService.save(restaurantsCurrent);
                return ResponseEntity.ok(restaurants);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (EntityNotFoundException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
 }
