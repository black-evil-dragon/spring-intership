package com.golgan.toduo.modules.users.exceptions;

import com.golgan.toduo.core.exceptions.ErrorType;
import org.springframework.http.HttpStatus;

public enum UserErrorType implements ErrorType {
    USER_NOT_FOUND("USER_NOT_FOUND", HttpStatus.NOT_FOUND, "Пользователь не найден"),
    EMAIL_ALREADY_EXISTS("USER_EMAIL_EXISTS", HttpStatus.BAD_REQUEST, "Email уже используется");

    private final String code;
    private final HttpStatus status;
    private final String defaultMessage;

    UserErrorType(String code, HttpStatus status, String defaultMessage) {
        this.code = code;
        this.status = status;
        this.defaultMessage = defaultMessage;
    }

    @Override public String getCode() { return code; }
    @Override public HttpStatus getStatus() { return status; }
    @Override public String getDefaultMessage() { return defaultMessage; }
}