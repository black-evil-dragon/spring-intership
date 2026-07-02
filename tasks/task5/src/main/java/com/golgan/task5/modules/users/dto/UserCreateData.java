package com.golgan.task5.modules.users.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;


public record UserCreateData(
    @NotBlank(message = "Email обязателен для заполнения")
    @Email(message = "Некорректный формат email")
    String email,

    @Size(min = 2, max = 50, message = "Пароль должен быть от 2 до 50 символов")
    String password
) {

}
