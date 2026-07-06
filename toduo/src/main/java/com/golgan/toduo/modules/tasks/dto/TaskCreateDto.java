package com.golgan.toduo.modules.tasks.dto;

import com.golgan.toduo.modules.tasks.models.TaskStatus;
import com.golgan.toduo.modules.users.dto.UserListDto;


import java.time.Instant;

public record TaskCreateDto(
    String name,

    TaskStatus status,
    String description,

    Instant deadline,

    UserListDto author,
    UserListDto assignee
) { }
