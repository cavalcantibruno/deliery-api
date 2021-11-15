package com.foop.delivery.api.exceptionhandler;

import lombok.Getter;

@Getter
public enum ProblemType {
    ENTITY_NOT_FOUND("/entity-not-found", "Entity Not Found"),
    ENTITY_IN_USE("/entity-in-use", "Entity In Use"),
    PARAMETER_INVALID("/param-invalid", "Parameter invalid"),
    BODY_WITH_PROBLEM("/body-with-problem", "Body With Problem"),
    RESOURCE__NOT_FOUND("/resource-not-found", "Resource Not Found"),
    SYSTEM_ERROR("/system-error", "System Error"),
    INVALID_DATA("/invalid-data", "Invalid Data"),
    DOMAIN_ERROR("/domain-error", "Domain Rule Violation");

    private String title;
    private String uri;

    ProblemType(String path, String title) {
        this.uri = "https://foop.com.br" + path;
        this.title = title;
    }
}
