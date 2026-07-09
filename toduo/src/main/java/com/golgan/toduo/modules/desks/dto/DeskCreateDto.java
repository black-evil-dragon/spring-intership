package com.golgan.toduo.modules.desks.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;



public record DeskCreateDto(
    @NotBlank(message = "Название доски не должно быть пустым")
    @Size(min = 2, max = 100, message = "Название доски должно быть от 2 до 100 символов")
    String name,

    // TODO: Мы можем прокидывать id пользователя
    //  из сохраненной сессии
    //  Но пока так
    @NotNull(message = "Владелец доски должен быть указан")
    Long ownerId
) {

}
