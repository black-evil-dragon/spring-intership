package com.golgan.toduo.core.config;

import java.time.Duration;

public final class TokenDuration {
    private TokenDuration() {}

    public static final Duration ACCESS_TOKEN_EXPIRATION = Duration.ofMinutes(15);

    public static final Duration REFRESH_TOKEN_EXPIRATION = Duration.ofDays(20);
}

