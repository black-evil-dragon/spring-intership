package com.golgan.toduo.modules.tasks.dto;

import com.golgan.toduo.modules.tasks.models.TaskStatus;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.Size;

import java.time.Instant;


public record TaskUpdateDto(
    @Size(min = 3, max = 255, message = "Название должно быть от 3 до 255 символов")
    String name,

    TaskStatus status,

    @Size(max = 4000, message = "Описание не должно превышать 4000 символов")
    String description,

    @Future(message = "Крайний срок должен быть в будущем")
    Instant deadline,

    Long columnId,

    Long authorId,

    Long assigneeId
) {
}
