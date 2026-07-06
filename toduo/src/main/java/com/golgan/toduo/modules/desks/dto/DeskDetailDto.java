package com.golgan.toduo.modules.desks.dto;

import com.golgan.toduo.modules.users.dto.UserListDto;

import java.time.Instant;
import java.util.List;

public record DeskDetailDto(
    Long id,
    String name,

    UserListDto owner,

    List<DeskColumnDetailDto> columns,

    Instant createdAt,
    Instant updatedAt
) {
}
