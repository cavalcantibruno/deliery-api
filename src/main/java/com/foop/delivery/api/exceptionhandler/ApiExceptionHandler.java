package com.foop.delivery.api.exceptionhandler;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.fasterxml.jackson.databind.exc.PropertyBindingException;
import com.foop.delivery.domain.exception.DomainException;
import com.foop.delivery.domain.exception.EntityInUseException;
import com.foop.delivery.domain.exception.EntityNotFoundException;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.TypeMismatchException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import com.fasterxml.jackson.databind.JsonMappingException.Reference;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {


    public static final String MSG_ERROR_GENERIC_USER = "An unexpected internal system error has occurred. "
            + "Try again and if the problem persists, contact us "
            + "with the system administrator.";

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleUncaught(Exception ex, WebRequest request) {
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
        ProblemType problemType = ProblemType.SYSTEM_ERROR;
        String detail = MSG_ERROR_GENERIC_USER;

        ex.printStackTrace();

        ProblemDetails problemDetails = createProblemBuilder(status, problemType, detail)
                .userMessage(MSG_ERROR_GENERIC_USER)
                .build();
        return handleExceptionInternal(ex, problemDetails, new HttpHeaders(), status, request);
    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers,
                                                                  HttpStatus status, WebRequest request) {
        Throwable rootCause = ExceptionUtils.getRootCause(ex);

        if (rootCause instanceof InvalidFormatException) {
            return handleInvalidFormat((InvalidFormatException) rootCause, headers, status, request);
        } else if (rootCause instanceof PropertyBindingException) {
            return handlePropertyBinding((PropertyBindingException) rootCause, headers, status, request);
        }

        ProblemType problemType = ProblemType.BODY_WITH_PROBLEM;
        String detail = "Body is invalid, check syntax error";
        ProblemDetails problemDetails = createProblemBuilder(status, problemType, detail)
                .userMessage(MSG_ERROR_GENERIC_USER)
                .build();

        return handleExceptionInternal(ex, problemDetails, new HttpHeaders(), status, request);
    }

    private ResponseEntity<Object> handlePropertyBinding(PropertyBindingException ex, HttpHeaders headers,
                                                         HttpStatus status, WebRequest request) {
        String path = joinPath(ex.getPath());
        ProblemType problemType = ProblemType.BODY_WITH_PROBLEM;

        String detail = String
                .format("The property '%s' does not exist. "
                        + "Correct or remove this property and try again.", path);

        ProblemDetails problemDetails = createProblemBuilder(status, problemType, detail)
                .userMessage(MSG_ERROR_GENERIC_USER)
                .build();
        return handleExceptionInternal(ex, problemDetails, new HttpHeaders(), status, request);

    }

    @Override
    protected ResponseEntity<Object> handleNoHandlerFoundException(NoHandlerFoundException ex,
                                                          HttpHeaders headers, HttpStatus status, WebRequest request) {

        ProblemType problemType = ProblemType.RESOURCE__NOT_FOUND;
        String detail = String.format("Resource %s you tried to access is non-existent.",
                ex.getRequestURL());

        ProblemDetails problemDetails = createProblemBuilder(status, problemType, detail)
                .userMessage(MSG_ERROR_GENERIC_USER)
                .build();

        return handleExceptionInternal(ex, problemDetails, headers, status, request);
    }

    @Override
    protected ResponseEntity<Object> handleTypeMismatch(TypeMismatchException ex, HttpHeaders headers,
                                                        HttpStatus status, WebRequest request) {

        if (ex instanceof MethodArgumentTypeMismatchException) {
            return handleMethodArgumentTypeMismatch(
                    (MethodArgumentTypeMismatchException) ex, headers, status, request);
        }

        return super.handleTypeMismatch(ex, headers, status, request);
    }

    private ResponseEntity<Object> handleMethodArgumentTypeMismatch(
            MethodArgumentTypeMismatchException ex, HttpHeaders headers,
            HttpStatus status, WebRequest request) {

        ProblemType problemType = ProblemType.PARAMETER_INVALID;

        String detail = String.format("URL parameter '%s' received value '%s'," +
                        "which is of an invalid type. Please correct and enter a compatible value for type %s.",
                ex.getName(), ex.getValue(), ex.getRequiredType().getSimpleName());

        ProblemDetails problemDetails = createProblemBuilder(status, problemType, detail)
                .userMessage(MSG_ERROR_GENERIC_USER)
                .build();

        return handleExceptionInternal(ex, problemDetails, headers, status, request);
    }

    private ResponseEntity<Object> handleInvalidFormat(InvalidFormatException ex, HttpHeaders headers,
                                                       HttpStatus status, WebRequest request) {

        String path = joinPath(ex.getPath());
        ProblemType problemType = ProblemType.BODY_WITH_PROBLEM;

        String detail = String
                .format("Property '%s' received a value '%s' which is of invalid type. " +
                "Correct and enter a value compatible with the type %s.",
                path, ex.getValue(), ex.getTargetType().getSimpleName());

        ProblemDetails problemDetails = createProblemBuilder(status, problemType, detail)
                .userMessage(MSG_ERROR_GENERIC_USER)
                .build();

        return handleExceptionInternal(ex, problemDetails, new HttpHeaders(), status, request);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<?> handlerEntityNotFound(EntityNotFoundException ex, WebRequest request) {
        HttpStatus status =  HttpStatus.NOT_FOUND;
        ProblemType problemType = ProblemType.ENTITY_NOT_FOUND;
        String detail = ex.getMessage();

        ProblemDetails problemDetails = createProblemBuilder(status, problemType, detail)
                .userMessage(MSG_ERROR_GENERIC_USER)
                .build();
        return handleExceptionInternal(ex, problemDetails, new HttpHeaders(), status, request);
    }

    @ExceptionHandler(DomainException.class)
    public ResponseEntity<?> handlerDomain(DomainException ex, WebRequest request) {
        HttpStatus status =  HttpStatus.BAD_REQUEST;
        ProblemType problemType = ProblemType.DOMAIN_ERROR;
        String detail = ex.getMessage();

        ProblemDetails problemDetails = createProblemBuilder(status, problemType, detail)
                .userMessage(MSG_ERROR_GENERIC_USER)
                .build();

        return handleExceptionInternal(ex, problemDetails, new HttpHeaders(), status, request);
    }

    @ExceptionHandler(EntityInUseException.class)
    public ResponseEntity<?> handlerEntityInUse(DomainException ex, WebRequest request) {
        HttpStatus status =  HttpStatus.CONFLICT;
        ProblemType problemType = ProblemType.ENTITY_IN_USE;
        String detail = ex.getMessage();

        ProblemDetails problemDetails = createProblemBuilder(status, problemType, detail)
                .userMessage(MSG_ERROR_GENERIC_USER)
                .build();

        return handleExceptionInternal(ex, problemDetails, new HttpHeaders(), status, request);
    }

    @Override
    protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers,
                                                             HttpStatus status, WebRequest request) {

        if(body == null) {
            body = ProblemDetails.builder()
                    .timestamp(LocalDateTime.now())
                    .title(status.getReasonPhrase())
                    .status(status.value())
                    .userMessage(MSG_ERROR_GENERIC_USER)
                    .build();

        } else if (body instanceof String) {
            body = ProblemDetails.builder()
                    .timestamp(LocalDateTime.now())
                    .title((String) body)
                    .status(status.value())
                    .userMessage(MSG_ERROR_GENERIC_USER)
                    .build();
        }

        return super.handleExceptionInternal(ex, body, headers, status, request);
    }

    private ProblemDetails.ProblemDetailsBuilder createProblemBuilder(HttpStatus status, ProblemType problemType,
                                                                      String detail) {
        return ProblemDetails.builder()
                .timestamp(LocalDateTime.now())
                .status(status.value())
                .type(problemType.getUri())
                .title(problemType.getTitle())
                .detail(detail);
    }

    private String joinPath(List<Reference> references) {
        return references.stream()
                .map(ref -> ref.getFieldName())
                .collect(Collectors.joining("."));
    }

}
