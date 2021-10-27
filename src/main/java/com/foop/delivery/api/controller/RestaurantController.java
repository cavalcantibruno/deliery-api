package com.foop.delivery.api.controller;

import com.foop.delivery.domain.model.Restaurant;
import com.foop.delivery.domain.repository.RestaurantRepository;
import com.foop.delivery.domain.service.RegisterRestaurantService;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@RestController
@RequestMapping("/restaurant")
public class RestaurantController {

    private final RestaurantRepository restaurantRepository;
    private final RegisterRestaurantService restaurantService;

    @GetMapping
    public List<Restaurant> list() {
        return restaurantRepository.findAll();
    }

    @GetMapping("/first")
    public Optional<Restaurant> findFirst() {
        return restaurantRepository.findFirst();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Restaurant> byId(@PathVariable Long id) {
        Restaurant restaurant = restaurantRepository
                .findById(id).orElseThrow(() -> new EntityNotFoundException("Not Found"));
        if(restaurant != null) {
            return ResponseEntity.ok(restaurant);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody Restaurant restaurant) {
        try {
            restaurant = restaurantService.save(restaurant);
            return ResponseEntity.status(HttpStatus.CREATED).body(restaurant);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody Restaurant restaurant) {
        try {
            Restaurant restaurantCurrent = restaurantRepository
                    .findById(id).orElseThrow(() -> new EntityNotFoundException("Not Found"));
            if (restaurantCurrent != null) {
                BeanUtils.copyProperties(restaurant, restaurantCurrent,
                        "id", "paymentMethods", "address", "createDate", "updateDate", "products");
                restaurantService.save(restaurantCurrent);
                return ResponseEntity.ok(restaurant);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (EntityNotFoundException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/free-shipping")
    public List<Restaurant> restaurantWithFreeShipping(String name) {
        return restaurantRepository.findWithFreeShipping(name);
    }
 }
