package com.foop.delivery.infrastructure.repository.spec;

import com.foop.delivery.domain.model.Restaurants;
import org.springframework.data.jpa.domain.Specification;

import java.math.BigDecimal;

public class RestaurantsSpecs {

    public static Specification<Restaurants> withFreeShipping() {
        return (root, query, builder) -> builder.equal(root.get("shippingFee"), BigDecimal.ZERO);
    }

    public static Specification<Restaurants> withSimilarName(String name) {
        return (root, query, builder) -> builder.like(root.get("name"),"%" + name + "%");
    }
}
