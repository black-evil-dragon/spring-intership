package com.golgan.toduo.modules.auth.dto;

public record TokenPairDto(
    AccessTokenDto accessToken,
    RefreshTokenDto refreshToken
) {
}
