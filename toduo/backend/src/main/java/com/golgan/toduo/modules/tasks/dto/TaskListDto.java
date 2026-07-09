package com.golgan.toduo.modules.tasks.dto;

import com.golgan.toduo.modules.tasks.models.TaskStatus;
import com.golgan.toduo.modules.users.dto.UserListDto;

public record TaskListDto(
    Long id,

    String name,
    TaskStatus status,

    UserListDto assignee,
    UserListDto author
) {
}
