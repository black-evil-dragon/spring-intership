package com.golgan.toduo.modules.users.dto;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;


public record UserCreateDto(
    @NotBlank(message = "Email не должен быть пустым")
    @Email(message = "Некорректный формат email")
    @Size(max = 100, message = "Email не должен превышать 100 символов")
    String email,

    @NotBlank(message = "Пароль не должен быть пустым")
    @Size(min = 8, max = 64, message = "Пароль должен быть от 8 до 64 символов")
    String password,

    @NotBlank(message = "Имя не должно быть пустым")
    @Size(min = 2, max = 50, message = "Имя должно быть от 2 до 50 символов")
    String firstName,

    @NotBlank(message = "Фамилия не должна быть пустой")
    @Size(min = 2, max = 50, message = "Фамилия должна быть от 2 до 50 символов")
    String lastName
) {
}