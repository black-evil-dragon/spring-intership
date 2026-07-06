package com.golgan.toduo.modules.users.dto;

public record UserDetailDto(
    Long id,
    String email,
    String firstName,
    String lastName
) {
}
