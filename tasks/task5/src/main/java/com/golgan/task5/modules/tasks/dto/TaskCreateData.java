package com.golgan.task5.modules.tasks.dto;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record TaskCreateData(
    @NotBlank(message = "Название обязательно для заполнения")
    @Size(min = 2, message = "Название слишком короткое")
    String title,

    @Size(min = 2, message = "Текст слишком короткий")
    String text,


    @NotNull(message = "Пользователь должен быть указан")
    Long userId
) {}
