package com.foop.delivery.domain.service.impl;

import com.foop.delivery.domain.exception.EntityInUseException;
import com.foop.delivery.domain.exception.EntityNotFoundException;
import com.foop.delivery.domain.exception.KitchenNotFoundException;
import com.foop.delivery.domain.model.Kitchen;
import com.foop.delivery.domain.repository.KitchenRepository;
import com.foop.delivery.domain.service.RegisterKitchenService;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class RegisterKitchenServiceImpl implements RegisterKitchenService {

    public static final String IS_IN_USE = "Kitchen with id %d cannot be removed, as it is in use";
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
            /**
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    String.format("Not exists a register Kitchen with id %d ", id));
            throw new EntityNotFoundException(
                    String.format("Not exists a register Kitchen with id %d ", id));
             */
            throw new KitchenNotFoundException(id);

        } catch (DataIntegrityViolationException ex) {
            throw new EntityInUseException(
                    String.format(IS_IN_USE, id));
        }
    }

    @Override
    public Kitchen findById(Long id) {
        return kitchenRepository
                .findById(id).orElseThrow(() -> new KitchenNotFoundException(id));
    }

}
