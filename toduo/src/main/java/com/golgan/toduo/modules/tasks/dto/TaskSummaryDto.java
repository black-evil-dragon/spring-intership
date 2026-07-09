package com.golgan.toduo.modules.tasks.dto;

import com.golgan.toduo.modules.tasks.models.TaskStatus;
import com.golgan.toduo.modules.users.dto.UserSummaryDto;

public record TaskSummaryDto(
    Long id,

    Long columnId,

    String name,
    TaskStatus status,

    UserSummaryDto assignee,
    UserSummaryDto author
) {
}
