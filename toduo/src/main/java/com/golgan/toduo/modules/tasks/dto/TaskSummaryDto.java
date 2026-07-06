package com.golgan.toduo.modules.tasks.dto;

import com.golgan.toduo.modules.tasks.models.TaskStatus;
import com.golgan.toduo.modules.users.dto.UserSummaryDto;

public record TaskSummaryDto(
    Long id,

    String name,
    TaskStatus status,
    Integer sort,

    UserSummaryDto assignee,
    UserSummaryDto author
) {
}
