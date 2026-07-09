package com.golgan.toduo.modules.echo.controllers;

import com.golgan.toduo.core.services.LoggerService;
import com.golgan.toduo.core.utils.IDGenerator;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
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

    private final ApplicationContext context;

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
        @Qualifier("upperLogService") LoggerService upperLogService,
        ApplicationContext context
    ) {
        this.lowerLogService = lowerLogService;
        this.upperLogService = upperLogService;

        // Практика 2
        this.context = context;
    }

    @GetMapping("/hello")
    @ResponseStatus(HttpStatus.OK)
    public String hello() {
        upperLogService.log("Эндпоинт работает! Хорошего дня)");
        lowerLogService.log("Кто-то посетил нашу страницу");
        return "Hello";
    }


    /// # Практика 2
    ///
    /// - Написать бин, который при инициализации загружает данные из properties и выводит их.
    /// - Реализовать prototype‑бин для генерации уникальных идентификаторов.
    @GetMapping("/generate-id")
    @ResponseStatus(HttpStatus.OK)
    public String generateId() {
        return context.getBean(IDGenerator.class).getUUID() + "-" + context.getBean(IDGenerator.class).getUUID();
    }
}
