package com.foop.delivery.domain.exception;

public class CityNotFoundException extends EntityNotFoundException {
    private static final long serialVersionUID = 1L;

    public CityNotFoundException(String message) {
        super(message);
    }

    public CityNotFoundException(Long id) {
        this(String.format("Not exists a register 'CITY' with id %d", id));
    }
}
