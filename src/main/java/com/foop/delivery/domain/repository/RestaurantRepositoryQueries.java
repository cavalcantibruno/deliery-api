package com.foop.delivery.domain.repository;

import com.foop.delivery.domain.model.Restaurant;

import java.math.BigDecimal;
import java.util.List;

public interface RestaurantRepositoryQueries {
    List<Restaurant> find(String name, BigDecimal shippingFeeStart, BigDecimal shippingFeeEnd);
    List<Restaurant> findWithFreeShipping(String name);
}
