package com.golgan.toduo.core.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.Instant;
import java.util.LinkedHashMap;
import java.util.Map;


/// Глобальный перехватчик исключений для веба приложения
///
/// Централизованно форматирует ответы об ошибках в унифицированный JSON формат
/// Скрывает внутренний стек от пользователей
@RestControllerAdvice
public class GlobalExceptionHandler {

    /// Перехватывает любые ожидаемые бизнес-ошибки
    ///
    @ExceptionHandler(HttpApplicationException.class)
    public ResponseEntity<Object> handleApplicationException(HttpApplicationException ex) {
        ErrorType errorType = ex.getErrorType();

        Map<String, Object> body = new LinkedHashMap<>();
        body.put("status", errorType.getStatus().value());
        body.put("code", errorType.getCode());
        body.put("message", ex.getMessage());
        body.put("timestamp", Instant.now());

        return new ResponseEntity<>(body, errorType.getStatus());
    }


    /// Перехватывает любые непредвиденные системные ошибки
    ///
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleUnhandledException(Exception exception) {
        Map<String, Object> body = new LinkedHashMap<>();

        // Здесь бы еще логировать куда-то

        body.put("status", HttpStatus.INTERNAL_SERVER_ERROR.value());
        body.put("code", "INTERNAL_SERVER_ERROR");
        body.put("message", "Произошла внутренняя ошибка сервера. Обратитесь в поддержку.");
        body.put("timestamp", Instant.now());


        return new ResponseEntity<>(body, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}