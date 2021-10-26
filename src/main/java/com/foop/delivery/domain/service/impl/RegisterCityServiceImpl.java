package com.foop.delivery.domain.service.impl;

import com.foop.delivery.domain.exception.EntityInUseException;
import com.foop.delivery.domain.model.City;
import com.foop.delivery.domain.model.Kitchen;
import com.foop.delivery.domain.repository.CityRepository;
import com.foop.delivery.domain.service.RegisterCityService;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

@Service
@AllArgsConstructor
public class RegisterCityServiceImpl implements RegisterCityService {
    private final CityRepository cityRepository;

    @Override
    public City save(City city) {
        return cityRepository.save(city);
    }

    @Override
    public void delete(Long id) {
        try {
            cityRepository.deleteById(id);
        } catch (EmptyResultDataAccessException ex) {
            throw new EntityNotFoundException(
                    String.format("Not exists a register city with id %d ", id));

        } catch (DataIntegrityViolationException ex) {
            throw new EntityInUseException(
                    String.format("City with id %d cannot be removed, as it is in use", id));
        }
    }
}
