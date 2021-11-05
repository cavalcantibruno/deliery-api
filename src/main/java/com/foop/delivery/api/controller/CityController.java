package com.foop.delivery.api.controller;

import com.foop.delivery.domain.exception.DomainException;
import com.foop.delivery.domain.exception.StateNotFoundException;
import com.foop.delivery.domain.model.City;
import com.foop.delivery.domain.repository.CityRepository;
import com.foop.delivery.domain.service.RegisterCityService;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/city")
public class CityController {

    private final CityRepository cityRepository;
    private final RegisterCityService cityService;

    @GetMapping
    public List<City> list() {
        return cityRepository.findAll();
    }

    @GetMapping("/{id}")
    public City byId(@PathVariable Long id) {
        return cityService.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public City save(@RequestBody City city) {
        try {
            return cityService.save(city);

        } catch (StateNotFoundException e) {
            throw  new DomainException(e.getMessage(), e);
        }
    }

    @PutMapping("/{id}")
    public City update(@PathVariable Long id,@RequestBody City city) {
        City cityCurrent = cityService.findById(id);
        BeanUtils.copyProperties(city, cityCurrent, "id");
        try {
            return cityService.save(cityCurrent);

        } catch (StateNotFoundException e) {
            throw  new DomainException(e.getMessage(), e);
        }
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        cityService.delete(id);
    }

}
