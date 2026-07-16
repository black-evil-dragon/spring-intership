package com.golgan.toduo.modules.auth.services;


import com.golgan.toduo.core.config.TokenDuration;
import com.golgan.toduo.modules.auth.dto.TokenPairDto;
import com.golgan.toduo.modules.auth.utils.JwtUtil;
import com.golgan.toduo.modules.users.models.UserEntity;
import com.golgan.toduo.modules.users.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
public class JwtService {
    private final JwtUtil jwtUtil;
    private final UserService userService;
    private final StringRedisTemplate redisTemplate;

    private static final Duration REFRESH_TIME = TokenDuration.REFRESH_TOKEN_EXPIRATION;


    // * ======================== GENERATE TOKENS ========================
    public TokenPairDto generateTokenPair(UserEntity user, String userIp) {
        String accessToken = jwtUtil.createAccessToken(user, userIp);
        String refreshToken = jwtUtil.createRefreshToken(user, userIp);

        redisTemplate.opsForValue().set(
            refreshToken,
            String.valueOf(user.getId()),
            REFRESH_TIME.toDays(),
            TimeUnit.DAYS
        );

        return new TokenPairDto(accessToken, refreshToken);
    }


    // * ======================== UPDATE TOKENS ========================
    public TokenPairDto updateTokens(String oldRefreshToken, String userIp) {
        if (oldRefreshToken == null || oldRefreshToken.isEmpty()) {
            throw new IllegalArgumentException("Refresh токен пустой");
        }

        // Ищем userId в Redis по старому токену
        Long userId = getUserIdByRefreshToken(oldRefreshToken);
        if (userId == null) {
            throw new RuntimeException("Refresh токен не найден или истек");
        }

        // Получаем пользователя из БД
        UserEntity user = userService.findById(userId);
        if (user == null) {
            throw new RuntimeException("Пользователь не найден");
        }

        redisTemplate.delete(oldRefreshToken);


        return generateTokenPair(user, userIp);
    }

    // * ======================== VALIDATION ========================
    public boolean validateAccessToken(String accessToken) {
        if (accessToken == null || accessToken.isEmpty()) {
            return false;
        }
        // Запрос проверяется stateless в памяти, Redis и БД здесь не дергаются
        return jwtUtil.validateToken(accessToken);
    }


    public TokenPairDto validateTokens(String accessToken, String refreshToken, String userIp) throws Exception {
        if (accessToken == null && refreshToken == null) {
            throw new Exception("Токены не предоставлены");
        }


        // * Access токен есть и он валидный
        if (accessToken != null && !accessToken.isEmpty() && jwtUtil.validateToken(accessToken)) {
            return new TokenPairDto(accessToken, refreshToken);
        }

        // * Access протух (или его нет), но есть refresh
        else if (refreshToken != null && !refreshToken.isEmpty()) {

            // Проверяем, что сам refresh токен валиден
            if (!jwtUtil.validateToken(refreshToken)) {
                return null;
            }

            Long userId = getUserIdByRefreshToken(refreshToken);
            UserEntity user = userService.findById(userId);

            return generateTokenPair(user, userIp);
        }

        // * Оба токена битые
        return null;
    }

    // * ======================== LOGOUT ========================
    public void logout(String refreshToken) {
        if (refreshToken != null && !refreshToken.isEmpty()) {
            redisTemplate.delete(refreshToken);
        }
    }

    // * ======================== UTILS ========================
    public Long getUserIdByRefreshToken(String refreshToken) {
        String userId = redisTemplate.opsForValue().get(refreshToken);
        if (userId == null) {
            return null;
        }
        return Long.parseLong(userId);
    }



}
