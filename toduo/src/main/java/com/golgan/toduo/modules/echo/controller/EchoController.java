package com.golgan.toduo.modules.echo.controller;

import com.golgan.toduo.core.services.LoggerService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/echo")
public class EchoController {

    private final LoggerService lowerLogService;
    private final LoggerService upperLogService;

    /// # Практика 1
    /// - Приложение из ДЗ должно реализовать интефейс логирования.
    /// ## Две реализации:
    /// - одна пишет лог заглавными буквами
    /// - вторая - строчными.
    ///
    /// И написать какую-нибудь простую бизнес логику, чтобы она вызывала логгер.
    /// Изучить работу аннотации `@Qualifier`, чтобы разрешить конфлит между двумя релизациями.
    public EchoController(
        @Qualifier("lowerLogService") LoggerService lowerLogService,
        @Qualifier("upperLogService") LoggerService upperLogService
    ) {
        this.lowerLogService = lowerLogService;
        this.upperLogService = upperLogService;
    }

    @GetMapping("/hello")
    @ResponseStatus(HttpStatus.OK)
    public String hello() {
        upperLogService.log("Эндпоинт работает! Хорошего дня)");
        lowerLogService.log("Кто-то посетил нашу страницу");
        return "Hello";
    }
}
