package com.foop.delivery.domain.service.impl;

import com.foop.delivery.domain.exception.EntityInUseException;
import com.foop.delivery.domain.model.Kitchen;
import com.foop.delivery.domain.repository.KitchenRepository;
import com.foop.delivery.domain.service.RegisterKitchenService;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
@AllArgsConstructor
public class RegisterKitchenServiceImpl implements RegisterKitchenService {

    private final KitchenRepository kitchenRepository;

    @Override
    public Kitchen save(Kitchen kitchen) {
        return kitchenRepository.save(kitchen);
    }

    @Override
    public void delete(Long id) {
        try {
            kitchenRepository.deleteById(id);

        } catch (EmptyResultDataAccessException ex) {
            throw new EntityNotFoundException(
                    String.format("Not exists a register Kitchen with id %d ", id));

        } catch (DataIntegrityViolationException ex) {
            throw new EntityInUseException(
                    String.format("Kitchen with id %d cannot be removed, as it is in use", id));
        }
    }

}
