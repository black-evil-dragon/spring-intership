package com.golgan.toduo.modules.auth.dto;

public record TokenPairDto(
    String accessToken,
    String refreshToken
) {
}
