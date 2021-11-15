package com.foop.delivery.api.controller;

import com.foop.delivery.domain.model.Kitchen;
import com.foop.delivery.domain.repository.KitchenRepository;
import com.foop.delivery.domain.service.RegisterKitchenService;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/kitchens")
public class KitchenController {

    private final KitchenRepository kitchenRepository;
    private final RegisterKitchenService kitchenService;

    @GetMapping
    public List<Kitchen> list() {
        return kitchenRepository.findAll();
    }

    @GetMapping("/{id}")
    public Kitchen byId(@PathVariable Long id) {
        return  kitchenService.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Kitchen save(@RequestBody Kitchen kitchen) {
        return kitchenService.save(kitchen);
    }

    @PutMapping("/{id}")
    public Kitchen update(@PathVariable Long id, @RequestBody @Valid Kitchen kitchen) {
        Kitchen kitchenCurrent = kitchenService.findById(id);
        BeanUtils.copyProperties(kitchen, kitchenCurrent, "id");
        return kitchenService.save(kitchenCurrent);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        kitchenService.delete(id);
    }


    @GetMapping("/byName")
    public List<Kitchen> kitchensByName(@RequestParam String name) {
       return kitchenRepository.findByName(name);
    }
}
