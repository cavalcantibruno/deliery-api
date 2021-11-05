package com.foop.delivery.domain.exception;

public class RestaurantNotFoundException extends EntityNotFoundException {
    private static final long serialVersionUID = 1L;

    public RestaurantNotFoundException(String message) {
        super(message);
    }

    public RestaurantNotFoundException(Long id) {
        this(String.format("Not exists a register 'RESTAURANT' with id %d", id));
    }
}
