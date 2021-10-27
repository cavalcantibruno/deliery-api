package com.foop.delivery.infrastructure.repository.spec;

import com.foop.delivery.domain.model.Restaurant;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.math.BigDecimal;

public class RestaurantWithFreeShippingSpec implements Specification<Restaurant> {

    @Override
    public Predicate toPredicate(Root<Restaurant> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder builder) {
        return builder.equal(root.get("shippingFee"), BigDecimal.ZERO);
    }
}
