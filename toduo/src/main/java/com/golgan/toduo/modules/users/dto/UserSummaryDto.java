package com.golgan.toduo.modules.users.dto;

public record UserSummaryDto(
    Long id,
    String email,
    String firstName,
    String lastName
) {
}
