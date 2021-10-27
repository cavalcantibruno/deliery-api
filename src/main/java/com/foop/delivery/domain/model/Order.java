package com.foop.delivery.domain.model;

import com.foop.delivery.domain.enums.EStatusOrder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    private BigDecimal subtotal;
    private BigDecimal shippingFee;
    private BigDecimal total;
    private EStatusOrder status;

    @CreationTimestamp
    private LocalDateTime creationDate;


    private LocalDateTime confirmationDate;
    private LocalDateTime cancellationDate;
    private LocalDateTime deliveryDate;

    @Embedded
    private Address address;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Restaurant restaurant;
    @ManyToOne
    @JoinColumn(name = "user_client_id", nullable = false)
    private User client;
    @ManyToOne
    @JoinColumn(nullable = false)
    private PaymentMethods paymentMethods;

    @OneToMany(mappedBy = "order")
    private List<OrderItem> items;
}
