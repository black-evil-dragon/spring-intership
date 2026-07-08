package com.golgan.toduo.modules.tasks.dto;

import com.golgan.toduo.modules.tasks.models.TaskStatus;
import com.golgan.toduo.modules.users.dto.UserSummaryDto;

import java.time.Instant;

public record TaskDetailDto(
    Long id,

    String name,
    TaskStatus status,
    Integer sort,

    String description,

    UserSummaryDto assignee,
    UserSummaryDto author,

    Instant createdAt,
    Instant updatedAt
) {
}
