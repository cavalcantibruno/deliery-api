package com.foop.delivery.domain.service.impl;

import com.foop.delivery.domain.exception.CityNotFoundException;
import com.foop.delivery.domain.exception.EntityInUseException;
import com.foop.delivery.domain.exception.EntityNotFoundException;
import com.foop.delivery.domain.model.City;
import com.foop.delivery.domain.model.State;
import com.foop.delivery.domain.repository.CityRepository;
import com.foop.delivery.domain.service.RegisterCityService;
import com.foop.delivery.domain.service.RegisterStateService;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;


@Service
@AllArgsConstructor
public class RegisterCityServiceImpl implements RegisterCityService {

    public static final String IS_IN_USE = "City with id %d cannot be removed, as it is in use";

    private final CityRepository cityRepository;
    private final RegisterStateService stateService;

    @Override
    public City save(City city) {
        Long stateId = city.getState().getId();
        State state = stateService.findById(stateId);
        city.setState(state);
        return cityRepository.save(city);
    }

    @Override
    public void delete(Long id) {
        try {
            cityRepository.deleteById(id);

        } catch (EmptyResultDataAccessException ex) {
            throw new CityNotFoundException(id);
        } catch (DataIntegrityViolationException ex) {
            throw new EntityInUseException(String.format(IS_IN_USE, id));
        }
    }

    @Override
    public City findById(Long id) {
        return cityRepository.findById(id).orElseThrow(() -> new CityNotFoundException(id));
    }
}
