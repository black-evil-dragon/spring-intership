package com.golgan.toduo.modules.tasks.exceptions;

import com.golgan.toduo.core.exceptions.ErrorType;
import org.springframework.http.HttpStatus;

public enum TaskErrorType implements ErrorType {
    TASK_NOT_FOUND("TASK_NOT_FOUND", HttpStatus.NOT_FOUND, "Задача не найдена");

    private final String code;
    private final HttpStatus status;
    private final String defaultMessage;

    TaskErrorType(String code, HttpStatus status, String defaultMessage) {
        this.code = code;
        this.status = status;
        this.defaultMessage = defaultMessage;
    }

    @Override public String getCode() { return code; }
    @Override public HttpStatus getStatus() { return status; }
    @Override public String getDefaultMessage() { return defaultMessage; }
}