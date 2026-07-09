package com.golgan.toduo.core.exceptions;

import lombok.Getter;


/// Базовый класс для всех контролируемых бизнес-исключений приложения
///
/// Все локальные исключения модулей, которые должны возвращать клиенту понятный
/// JSON-ответ со специфичным кодом ошибки, обязаны наследоваться от этого класса
public abstract class HttpApplicationException extends RuntimeException {
    @Getter
    private final ErrorType errorType;

    protected HttpApplicationException(ErrorType errorType) {
        super(errorType.getDefaultMessage());
        this.errorType = errorType;
    }


    protected HttpApplicationException(ErrorType errorType, String customMessage) {
        super(customMessage);
        this.errorType = errorType;
    }

}
