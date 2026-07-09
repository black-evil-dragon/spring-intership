package com.golgan.toduo.modules.users.dto;


import jakarta.validation.constraints.Size;


public record UserUpdateDto(
    @Size(min = 2, max = 50, message = "Имя должно быть от 2 до 50 символов")
    String firstName,

    @Size(min = 2, max = 50, message = "Фамилия должна быть от 2 до 50 символов")
    String lastName
) {
}