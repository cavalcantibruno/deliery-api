package com.foop.delivery.api.controller;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.foop.delivery.domain.exception.DomainException;
import com.foop.delivery.domain.exception.EntityNotFoundException;
import com.foop.delivery.domain.exception.ValidateException;
import com.foop.delivery.domain.model.Restaurant;
import com.foop.delivery.domain.repository.RestaurantRepository;
import com.foop.delivery.domain.service.RegisterRestaurantService;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.util.ReflectionUtils;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.SmartValidator;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@AllArgsConstructor
@RestController
@RequestMapping("/restaurant")
public class RestaurantController {

    private final RestaurantRepository restaurantRepository;
    private final RegisterRestaurantService restaurantService;
    private final SmartValidator validator;

    @GetMapping
    public List<Restaurant> list() {
        return restaurantRepository.findAll();
    }

    @GetMapping("/first")
    public Optional<Restaurant> findFirst() {
        return restaurantRepository.findFirst();
    }

    @GetMapping("/{id}")
    public Restaurant byId(@PathVariable Long id) {
        return restaurantService.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Restaurant save(@RequestBody @Valid Restaurant restaurant) {
        try {
            return restaurantService.save(restaurant);
        } catch (EntityNotFoundException ex) {
            throw new DomainException(ex.getMessage(), ex);
        }
    }

    @PutMapping("/{id}")
    public Restaurant update(@PathVariable Long id, @RequestBody @Valid Restaurant restaurant) {
        Restaurant restaurantCurrent = restaurantService.findById(id);
        BeanUtils.copyProperties(restaurant, restaurantCurrent,
                "id", "paymentMethods", "address", "createDate", "updateDate", "products");
        try {
            return restaurantService.save(restaurantCurrent);
        } catch (EntityNotFoundException ex) {
            throw new DomainException(ex.getMessage(), ex);
        }
    }

    @GetMapping("/free-shipping")
    public List<Restaurant> restaurantWithFreeShipping(String name) {
        return restaurantRepository.findWithFreeShipping(name);
    }

    @PatchMapping("/{restauranteId}")
    public Restaurant atualizarParcial(@PathVariable Long id, @RequestBody Map<String, Object> fields,
                                       HttpServletRequest request) {
        Restaurant restaurantCurrent = restaurantService.findById(id);
        merge(fields, restaurantCurrent, request);
        validate(restaurantCurrent, "restaurant");
        return update(id, restaurantCurrent);
    }

    private void validate(Restaurant restaurant, String objectName) {
        BeanPropertyBindingResult bindingResult = new BeanPropertyBindingResult(restaurant, objectName);
        validator.validate(restaurant, bindingResult);

        if (bindingResult.hasErrors()) {
            throw new ValidateException(bindingResult);
        }
    }

    private void merge(Map<String, Object> dadosOrigem, Restaurant restaurantDest, HttpServletRequest request) {
        ServletServerHttpRequest serverHttpRequest = new ServletServerHttpRequest(request);
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.configure(DeserializationFeature.FAIL_ON_IGNORED_PROPERTIES, true);
            objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, true);

            Restaurant restaurantOrig = objectMapper.convertValue(dadosOrigem, Restaurant.class);

            dadosOrigem.forEach((nameProperties, valueProperties) -> {
                Field field = ReflectionUtils.findField(Restaurant.class, nameProperties);
                field.setAccessible(true);
                Object newValue = ReflectionUtils.getField(field, restaurantOrig);
                ReflectionUtils.setField(field, restaurantDest, newValue);
            });

        } catch (IllegalArgumentException e) {
            Throwable rootCause = ExceptionUtils.getRootCause(e);
            throw new HttpMessageNotReadableException(e.getMessage(), rootCause, serverHttpRequest);
        }
    }
 }
