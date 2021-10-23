package com.foop.delivery.api.controller;

import com.foop.delivery.api.model.KitchensWrapper;
import com.foop.delivery.domain.exception.EntityInUseException;
import com.foop.delivery.domain.model.Kitchen;
import com.foop.delivery.domain.repository.KitchenRepository;
import com.foop.delivery.domain.service.RegisterKitchenService;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/kitchens")
public class KitchenController {

    private final KitchenRepository kitchenRepository;
    private final RegisterKitchenService kitchenService;

    @GetMapping
    public List<Kitchen> list() {
        return kitchenRepository.list();
    }

    @GetMapping(produces = MediaType.APPLICATION_XML_VALUE)
    public KitchensWrapper listXml() {
        return new KitchensWrapper(kitchenRepository.list());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Kitchen> byId(@PathVariable Long id) {
        Kitchen kitchen = kitchenRepository.byId(id);
        if(kitchen != null) {
            return ResponseEntity.ok(kitchen);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Kitchen save(@RequestBody Kitchen kitchen) {
        return kitchenService.save(kitchen);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Kitchen> update(@PathVariable Long id, @RequestBody Kitchen kitchen) {
        Kitchen kitchenCurrent = kitchenRepository.byId(id);
        if (kitchenCurrent != null) {
            BeanUtils.copyProperties(kitchen, kitchenCurrent, "id");
            kitchenService.save(kitchenCurrent);
            return ResponseEntity.ok(kitchenCurrent);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Kitchen> delete(@PathVariable Long id) {
        try {
            kitchenService.delete(id);
            return ResponseEntity.noContent().build();

        } catch (EntityNotFoundException ex) {
            return ResponseEntity.notFound().build();

        } catch (EntityInUseException ex) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }
}
