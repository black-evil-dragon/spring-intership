package com.golgan.toduo.modules.auth.exceptions;

import com.golgan.toduo.core.exceptions.ErrorType;
import org.springframework.http.HttpStatus;

public enum AuthErrorType implements ErrorType {
    AUTH_MISS_MATCH("AUTH_MISS_MATCH", HttpStatus.UNAUTHORIZED, "Учетные данные неверны");

    private final String code;
    private final HttpStatus status;
    private final String defaultMessage;

    AuthErrorType(String code, HttpStatus status, String defaultMessage) {
        this.code = code;
        this.status = status;
        this.defaultMessage = defaultMessage;
    }

    @Override public String getCode() { return code; }
    @Override public HttpStatus getStatus() { return status; }
    @Override public String getDefaultMessage() { return defaultMessage; }
}