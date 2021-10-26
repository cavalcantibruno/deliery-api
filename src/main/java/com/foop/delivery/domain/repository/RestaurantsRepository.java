package com.foop.delivery.domain.repository;

import com.foop.delivery.domain.model.Restaurants;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface RestaurantsRepository
        extends CustomJpaRepository<Restaurants, Long>, RestaurantsRepositoryQueries, JpaSpecificationExecutor<Restaurants> {
    List<Restaurants> byName(String name, Long id);


}
