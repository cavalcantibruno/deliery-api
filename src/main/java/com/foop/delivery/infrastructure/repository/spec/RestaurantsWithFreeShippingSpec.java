package com.foop.delivery.infrastructure.repository.spec;

import com.foop.delivery.domain.model.Restaurants;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.math.BigDecimal;

public class RestaurantsWithFreeShippingSpec implements Specification<Restaurants> {

    @Override
    public Predicate toPredicate(Root<Restaurants> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder builder) {
        return builder.equal(root.get("shippingFee"), BigDecimal.ZERO);
    }
}
