package com.foop.delivery.infrastructure.repository;

import com.foop.delivery.domain.model.Kitchen;
import com.foop.delivery.domain.repository.KitchenRepository;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Component
public class KitchenRepositoryImpl {
    @PersistenceContext
    private EntityManager manager;


    public List<Kitchen> list() {
        return manager.createQuery("from Kitchen", Kitchen.class).getResultList();
    }


    public Kitchen byId(Long id) {
        return manager.find(Kitchen.class, id);
    }


    @Transactional
    public Kitchen save(Kitchen kitchen) {
        return manager.merge(kitchen);
    }


    @Transactional
    public void remove(Long id) {
        Kitchen kitchen = byId(id);
        if(kitchen == null) {
            throw new EmptyResultDataAccessException(1);
        }
        manager.remove(kitchen);
    }

    public List<Kitchen> findByName(String name) {
        return manager
                .createQuery("from Kitchen where name like :name", Kitchen.class)
                .setParameter("name", "%" + name + "%")
                .getResultList();
    }
}
