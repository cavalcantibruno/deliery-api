package com.foop.delivery.domain.service;

import com.foop.delivery.domain.model.State;
import org.springframework.stereotype.Component;

@Component
public interface RegisterStateService {
    State save(State state);
    void delete(Long id);
}
