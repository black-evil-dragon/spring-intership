package com.golgan.toduo.modules.tasks.dto;

import com.golgan.toduo.modules.desks.dto.DeskColumnSummaryDto;
import com.golgan.toduo.modules.tasks.models.TaskStatus;
import com.golgan.toduo.modules.users.dto.UserSummaryDto;

import java.time.Instant;

public record TaskDetailDto(
    Long id,

    DeskColumnSummaryDto column,

    String name,
    TaskStatus status,

    String description,
    String deadline,

    UserSummaryDto assignee,
    UserSummaryDto author,

    Instant createdAt,
    Instant updatedAt
) {
}
