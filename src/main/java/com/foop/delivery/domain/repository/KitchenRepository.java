package com.foop.delivery.domain.repository;

import com.foop.delivery.domain.model.Kitchen;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface KitchenRepository extends JpaRepository<Kitchen, Long> {
    List<Kitchen> findByName(String name);
}
