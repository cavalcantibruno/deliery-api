package com.foop.delivery.api.controller;

import com.foop.delivery.domain.model.State;
import com.foop.delivery.domain.repository.StateRepository;
import com.foop.delivery.domain.service.RegisterStateService;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/state")
public class StateController {

    private final StateRepository stateRepository;
    private final RegisterStateService stateService;

    @GetMapping
    public List<State> list() {
        return stateRepository.findAll();
    }

    @GetMapping("/{id}")
    public State byId(@PathVariable Long id) {
        return stateService.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public State save(@RequestBody @Valid State state) {
        return stateService.save(state);
    }

    @PutMapping("/{id}")
    public State update(@PathVariable Long id,@RequestBody @Valid State state) {
        State stateCurrent = stateService.findById(id);
        BeanUtils.copyProperties(state, stateCurrent, "id");
        return stateService.save(stateCurrent);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        stateService.delete(id);
    }
}
