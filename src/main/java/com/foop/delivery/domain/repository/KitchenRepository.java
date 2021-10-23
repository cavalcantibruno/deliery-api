package com.foop.delivery.domain.repository;

import com.foop.delivery.domain.model.Kitchen;

import java.util.List;

public interface KitchenRepository {
    List<Kitchen> list();
    Kitchen byId(Long id);
    Kitchen save(Kitchen kitchen);
    void remove(Long id);
}
