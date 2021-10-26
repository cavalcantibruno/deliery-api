package com.foop.delivery.infrastructure.repository.spec;

import com.foop.delivery.domain.model.Restaurants;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.math.BigDecimal;

@AllArgsConstructor
public class RestaurantsWithSimilarNameSpec implements Specification<Restaurants> {

    private String name;

    @Override
    public Predicate toPredicate(Root<Restaurants> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder builder) {
        return builder.like(root.get("name"),"%" + name + "%");
    }
}
