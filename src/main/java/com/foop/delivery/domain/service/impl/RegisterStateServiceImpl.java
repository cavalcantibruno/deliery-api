package com.foop.delivery.domain.service.impl;

import com.foop.delivery.domain.exception.EntityInUseException;
import com.foop.delivery.domain.exception.EntityNotFoundException;
import com.foop.delivery.domain.exception.StateNotFoundException;
import com.foop.delivery.domain.model.State;
import com.foop.delivery.domain.repository.StateRepository;
import com.foop.delivery.domain.service.RegisterStateService;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@AllArgsConstructor
@Service
public class RegisterStateServiceImpl implements RegisterStateService {
    public static final String STATE_IN_USE = "State with id %d cannot be removed, as it is in use";
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
            throw new StateNotFoundException(id);
        } catch (DataIntegrityViolationException ex) {
            throw new StateNotFoundException(String.format(STATE_IN_USE, id));
        }
    }

    @Override
    public State findById(Long id) {
        return stateRepository.findById(id).orElseThrow(() -> new StateNotFoundException(id));
    }
}
