package com.foop.delivery.api.controller;

import com.foop.delivery.domain.model.Restaurants;
import com.foop.delivery.domain.repository.RestaurantsRepository;
import com.foop.delivery.domain.service.RegisterRestaurantService;
import com.foop.delivery.infrastructure.repository.spec.RestaurantsSpecs;
import com.foop.delivery.infrastructure.repository.spec.RestaurantsWithFreeShippingSpec;
import com.foop.delivery.infrastructure.repository.spec.RestaurantsWithSimilarNameSpec;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

import static com.foop.delivery.infrastructure.repository.spec.RestaurantsSpecs.*;

@AllArgsConstructor
@RestController
@RequestMapping("/restaurants")
public class RestaurantController {

    private final RestaurantsRepository restaurantsRepository;
    private final RegisterRestaurantService restaurantService;

    @GetMapping
    public List<Restaurants> list() {
        return restaurantsRepository.findAll();
    }

    @GetMapping("/first")
    public Optional<Restaurants> findFirst() {
        return restaurantsRepository.findFirst();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Restaurants> byId(@PathVariable Long id) {
        Restaurants restaurant = restaurantsRepository
                .findById(id).orElseThrow(() -> new EntityNotFoundException("Not Found"));
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
            Restaurants restaurantsCurrent = restaurantsRepository
                    .findById(id).orElseThrow(() -> new EntityNotFoundException("Not Found"));
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

    @GetMapping("/free-shipping")
    public List<Restaurants> restaurantsWithFreeShipping(String name) {
        return restaurantsRepository.findWithFreeShipping(name);
    }
 }
