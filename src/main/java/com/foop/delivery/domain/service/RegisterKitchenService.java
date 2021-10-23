package com.foop.delivery.domain.service;

import com.foop.delivery.domain.model.Kitchen;
import org.springframework.stereotype.Component;

@Component
public interface RegisterKitchenService {
    Kitchen save(Kitchen kitchen);
    void delete(Long id);
}
