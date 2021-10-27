package com.foop.delivery.domain.repository;

import com.foop.delivery.domain.model.Restaurant;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RestaurantRepository
        extends CustomJpaRepository<Restaurant, Long>, RestaurantRepositoryQueries, JpaSpecificationExecutor<Restaurant> {

    @Query("from Restaurant r join r.kitchen left join fetch r.paymentMethods")
    List<Restaurant> findAll();
    List<Restaurant> byName(String name, Long id);
}
