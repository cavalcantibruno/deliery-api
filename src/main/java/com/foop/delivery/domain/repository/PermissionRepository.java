package com.foop.delivery.domain.repository;

import com.foop.delivery.domain.model.Permission;

import java.util.List;

public interface PermissionRepository {
    List<Permission> list();
    Permission byId(Long id);
    Permission save(Permission permission);
    void remove(Permission permission);
}
