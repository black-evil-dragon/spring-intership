package com.golgan.toduo.modules.desks.dto;

import jakarta.validation.constraints.*;

public record DeskColumnCreateDto(
    @NotBlank(message = "Название колонки не должно быть пустым")
    @Size(min = 1, max = 50, message = "Название колонки должно быть от 1 до 50 символов")
    String name,

    Integer newPosition
) {
}