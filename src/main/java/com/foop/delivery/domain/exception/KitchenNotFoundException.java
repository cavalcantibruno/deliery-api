package com.foop.delivery.domain.exception;

public class KitchenNotFoundException extends EntityNotFoundException {
    private static final long serialVersionUID = 1L;

    public KitchenNotFoundException(String message) {
        super(message);
    }

    public KitchenNotFoundException(Long id) {
        this(String.format("Not exists a register 'KITCHEN' with id %d", id));
    }
}
