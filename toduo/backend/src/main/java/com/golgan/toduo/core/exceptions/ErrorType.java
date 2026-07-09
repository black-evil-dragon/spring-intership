package com.golgan.toduo.core.exceptions;

import org.springframework.http.HttpStatus;

/// Единый интерфейс для типов ошибок всей приложения
///
/// Каждый функциональный модуль (users, desks и т.д.) должен объявлять свой собственный
/// Enum, реализующий этот интерфейс, для изоляции кодов ошибок внутри модуля
///
public interface ErrorType {
    String getCode();
    HttpStatus getStatus();
    String getDefaultMessage();
}