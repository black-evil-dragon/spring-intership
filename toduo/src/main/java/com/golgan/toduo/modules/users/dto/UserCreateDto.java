package com.golgan.toduo.modules.users.dto;


// TODO Валидация
public record UserCreateDto(
    String email,

    String password,

    String firstName,

    String lastName
) {
}
