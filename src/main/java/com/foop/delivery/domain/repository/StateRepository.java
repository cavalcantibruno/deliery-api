package com.foop.delivery.domain.repository;

import com.foop.delivery.domain.model.State;

import java.util.List;

public interface StateRepository {
    List<State> list();
    State byId(Long id);
    State save(State state);
    void remove(Long id);
}
