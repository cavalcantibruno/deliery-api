package com.foop.delivery.domain.service.impl;

import com.foop.delivery.domain.exception.EntityInUseException;
import com.foop.delivery.domain.model.Kitchen;
import com.foop.delivery.domain.model.State;
import com.foop.delivery.domain.repository.StateRepository;
import com.foop.delivery.domain.service.RegisterStateService;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;

@AllArgsConstructor
@Service
public class RegisterStateServiceImpl implements RegisterStateService {

    private final StateRepository stateRepository;

    @Override
    @Transactional
    public State save(State state) {
        return stateRepository.save(state);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        try {
            stateRepository.deleteById(id);
        } catch (EmptyResultDataAccessException ex) {
            throw new EntityNotFoundException(
                    String.format("Not exists a register state with id %d ", id));

        } catch (DataIntegrityViolationException ex) {
            throw new EntityInUseException(
                    String.format("State with id %d cannot be removed, as it is in use", id));
        }
    }
}
