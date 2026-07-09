package com.golgan.toduo.modules.tasks.dto;

import com.golgan.toduo.modules.tasks.models.TaskStatus;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.Instant;



public record TaskCreateDto(
    @NotNull(message = "Доска задачи должна быть указана")
    Long deskId,

    Long columnId,

    @NotBlank(message = "Название задачи не должно быть пустым")
    @Size(min = 3, max = 255, message = "Название должно быть от 3 до 255 символов")
    String name,

    // TODO По-хорошему надо добавить валидацию, но ее похоже надо писать вручную
    @NotNull(message = "Статус задачи должен быть указан")
    TaskStatus status,

    @Size(max = 4000, message = "Описание не должно превышать 4000 символов")
    String description,

    @Future(message = "Крайний срок должен быть в будущем")
    Instant deadline,

    @NotNull(message = "Автор задачи должен быть указан")
    Long authorId,

    Long assigneeId
) {
}
