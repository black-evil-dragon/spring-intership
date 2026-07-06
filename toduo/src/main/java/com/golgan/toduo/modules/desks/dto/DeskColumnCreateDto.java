package com.golgan.toduo.modules.desks.dto;

import jakarta.validation.constraints.*;

public record DeskColumnCreateDto(
    @NotNull(message = "ID доски должен быть указан")
    Long deskId,

    @NotBlank(message = "Название колонки не должно быть пустым")
    @Size(min = 1, max = 50, message = "Название колонки должно быть от 1 до 50 символов")
    String name,

    @NotNull(message = "Позиция колонки должна быть указана")
    @PositiveOrZero(message = "Позиция колонки не может быть отрицательной")
    Integer sort
) {
}