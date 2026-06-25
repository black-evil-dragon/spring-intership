package com.golgan.toduo.core.utils;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.UUID;


/// # Практика 2
///
/// - Написать бин, который при инициализации загружает данные из properties и выводит их.
/// - Реализовать prototype‑бин для генерации уникальных идентификаторов.
@Component
@Scope("prototype")
public class IDGenerator {

    private final String id;


    // ! Чисто эксперимент, не имеющий практического смысла. Смотрю как работает Value и prototype
    public IDGenerator(@Value("${data.id.length}") int uuidLength) {
        String newUuid = UUID.randomUUID().toString().replace("-", "");
        this.id = newUuid.substring(0, Math.min(uuidLength, newUuid.length()));
    }

    public String getUUID() {
        return id;
    }
}