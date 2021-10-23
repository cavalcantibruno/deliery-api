package com.foop.delivery.api.controller;

import com.foop.delivery.domain.exception.EntityInUseException;
import com.foop.delivery.domain.model.Kitchen;
import com.foop.delivery.domain.model.State;
import com.foop.delivery.domain.repository.StateRepository;
import com.foop.delivery.domain.service.RegisterStateService;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/state")
public class StateController {

    private final StateRepository stateRepository;
    private final RegisterStateService stateService;

    @GetMapping
    public List<State> list() {
        return stateRepository.list();
    }

    @GetMapping("/{id}")
    public ResponseEntity<State> byId(@PathVariable Long id) {
        State state = stateRepository.byId(id);
        if(state != null) {
            return ResponseEntity.ok(state);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<State> save(@RequestBody State state) {
        return ResponseEntity.status(HttpStatus.CREATED).body(stateService.save(state));
    }

    @PutMapping("/{id}")
    public ResponseEntity<State> update(@PathVariable Long id,@RequestBody State state) {
        State stateCurrent = stateRepository.byId(id);
        if(stateCurrent != null) {
            BeanUtils.copyProperties(state, stateCurrent, "id");
            stateService.save(stateCurrent);
            return ResponseEntity.ok(stateCurrent);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<State> delete(@PathVariable Long id) {
        try {
            stateService.delete(id);
            return ResponseEntity.noContent().build();

        } catch (EntityNotFoundException ex) {
            return ResponseEntity.notFound().build();

        } catch (EntityInUseException ex) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }
}
