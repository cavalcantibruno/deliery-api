package com.foop.delivery.domain.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.math.BigDecimal;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Product {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    @Id
    private Long id;

    private String name;
    private String description;
    private BigDecimal price;
    private Boolean active;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Restaurant restaurant;
}
