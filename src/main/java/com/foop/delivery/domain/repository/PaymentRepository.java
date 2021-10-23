package com.foop.delivery.domain.repository;

import com.foop.delivery.domain.model.Payment;

import java.util.List;

public interface PaymentRepository {
    List<Payment> list();
    Payment byId(Long id);
    Payment save(Payment payment);
    void remove(Payment payment);
}
