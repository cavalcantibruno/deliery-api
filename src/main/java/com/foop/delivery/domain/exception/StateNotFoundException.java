package com.foop.delivery.domain.exception;

public class StateNotFoundException extends EntityNotFoundException {
    private static final long serialVersionUID = 1L;

    public StateNotFoundException(String message) {
        super(message);
    }

    public StateNotFoundException(Long id) {
        this(String.format("Not exists a register 'STATE' with id %d", id));
    }
}
