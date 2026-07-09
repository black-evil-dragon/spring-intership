package com.golgan.toduo.modules.desks.exceptions;

import com.golgan.toduo.core.exceptions.ErrorType;
import org.springframework.http.HttpStatus;

public enum DeskErrorType implements ErrorType {
    DESK_NOT_FOUND("DESK_NOT_FOUND", HttpStatus.NOT_FOUND, "Доска не найдена"),
    COLUMN_MISMATCH("COLUMN_MISMATCH", HttpStatus.BAD_REQUEST, "Указанная колонка не принадлежит этой доске"),
    COLUMN_NOT_FOUND("COLUMN_NOT_FOUND", HttpStatus.NOT_FOUND, "Колонка не найдена");

    private final String code;
    private final HttpStatus status;
    private final String defaultMessage;

    DeskErrorType(String code, HttpStatus status, String defaultMessage) {
        this.code = code;
        this.status = status;
        this.defaultMessage = defaultMessage;
    }

    @Override public String getCode() { return code; }
    @Override public HttpStatus getStatus() { return status; }
    @Override public String getDefaultMessage() { return defaultMessage; }
}