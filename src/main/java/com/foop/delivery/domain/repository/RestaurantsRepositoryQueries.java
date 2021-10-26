package com.foop.delivery.domain.repository;

import com.foop.delivery.domain.model.Restaurants;

import java.math.BigDecimal;
import java.util.List;

public interface RestaurantsRepositoryQueries {
    List<Restaurants> find(String name, BigDecimal shippingFeeStart, BigDecimal shippingFeeEnd);
    List<Restaurants> findWithFreeShipping(String name);
}
