package com.foop.delivery.domain.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.validation.BindingResult;

@AllArgsConstructor
@Getter
public class ValidateException extends RuntimeException {
    private static final long serialVersionUID = 1L;
    private BindingResult bindingResult;
}
