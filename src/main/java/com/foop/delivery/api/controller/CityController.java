package com.foop.delivery.api.controller;

import com.foop.delivery.domain.exception.EntityInUseException;
import com.foop.delivery.domain.model.City;
import com.foop.delivery.domain.repository.CityRepository;
import com.foop.delivery.domain.service.RegisterCityService;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
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
    public ResponseEntity<City> byId(@PathVariable Long id) {
        City city = cityRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Not found"));
        if(city != null) {
            return ResponseEntity.ok(city);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<City> save(@RequestBody City city) {
        return ResponseEntity.status(HttpStatus.CREATED).body(cityService.save(city));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id,@RequestBody City city) {
        try {
            City stateCurrent = cityRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Not found"));
            if(stateCurrent != null) {
                BeanUtils.copyProperties(city, stateCurrent, "id");
                cityService.save(stateCurrent);
                return ResponseEntity.ok(stateCurrent);
            }
            return ResponseEntity.notFound().build();
        } catch (EntityNotFoundException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (EntityInUseException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<City> delete(@PathVariable Long id) {
        try {
            cityService.delete(id);
            return ResponseEntity.noContent().build();

        } catch (EntityNotFoundException ex) {
            return ResponseEntity.notFound().build();

        } catch (EntityInUseException ex) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }


}
