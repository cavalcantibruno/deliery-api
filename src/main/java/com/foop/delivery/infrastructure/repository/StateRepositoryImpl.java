package com.foop.delivery.infrastructure.repository;

import com.foop.delivery.domain.model.Kitchen;
import com.foop.delivery.domain.model.State;
import com.foop.delivery.domain.repository.StateRepository;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Component
public class StateRepositoryImpl {
    @PersistenceContext
    private EntityManager manager;

    
    public List<State> list() {
        return manager.createQuery("from State", State.class).getResultList();
    }

    
//    public State byId(Long id) {
//        return manager.find(State.class, id);
//    }

    
    @Transactional
    public State save(State state) {
        return manager.merge(state);
    }

    
//    @Transactional
//    public void remove(Long id) {
//        State state = byId(id);
//        if(state == null) {
//            throw new EmptyResultDataAccessException(1);
//        }
//        manager.remove(state);
//    }
}
