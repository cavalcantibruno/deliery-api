package com.foop.delivery.infrastructure.repository.spec;

import com.foop.delivery.domain.model.Restaurant;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

@AllArgsConstructor
public class RestaurantWithSimilarNameSpec implements Specification<Restaurant> {

    private String name;

    @Override
    public Predicate toPredicate(Root<Restaurant> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder builder) {
        return builder.like(root.get("name"),"%" + name + "%");
    }
}
