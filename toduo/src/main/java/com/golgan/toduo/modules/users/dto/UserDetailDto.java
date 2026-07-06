package com.golgan.toduo.modules.users.dto;

import java.time.Instant;

public record UserDetailDto(
    Long id,
    String email,
    String firstName,
    String lastName,

    Instant createdAt
) {
}
