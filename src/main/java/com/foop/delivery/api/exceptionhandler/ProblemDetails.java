package com.foop.delivery.api.exceptionhandler;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;


@JsonInclude(JsonInclude.Include.NON_NULL)
@Builder
@Getter
public class ProblemDetails {
    private Integer status;
    private String type;
    private String title;
    private String detail;
    private String userMessage;
    private LocalDateTime timestamp;
}
