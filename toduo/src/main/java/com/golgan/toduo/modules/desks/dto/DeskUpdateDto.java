package com.golgan.toduo.modules.desks.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;


public record DeskUpdateDto(
    @Size(min = 2, max = 100, message = "Название доски должно быть от 2 до 100 символов")
    String name,

    Long ownerId
) {

}
