package com.golgan.toduo.modules.auth.utils;

import com.golgan.toduo.core.config.TokenDuration;
import com.golgan.toduo.modules.users.models.UserEntity;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.time.Instant;
import java.util.Date;

@Component
public class JwtUtil {

    private final SecretKey key;
    private static final Duration ACCESS_TIME = TokenDuration.ACCESS_TOKEN_EXPIRATION;
    private static final Duration REFRESH_TIME = TokenDuration.REFRESH_TOKEN_EXPIRATION;



    public JwtUtil(@Value("${jwt.secret}") String secretString) {
        this.key = Keys.hmacShaKeyFor(secretString.getBytes(StandardCharsets.UTF_8));
    }


    // * Генерация токена
    public String generateToken(
        UserEntity user,
        Duration expirationTime,
        String ipAddress,
        String type
    ) {

        JwtBuilder builder = Jwts.builder()
            .setSubject(user.getId().toString())
            .claim("type", type)
            .claim("ip", ipAddress)
            .setIssuedAt(new Date())
            .setExpiration(Date.from(
                    Instant.now().plus(expirationTime)
                )
            )
            .signWith(key);

        // Добавляем лишние клеймы только в Access токен
        if ("ACCESS".equals(type)) {
            builder.claim("email", user.getEmail());
        }

        return builder.compact();
    }



    // * Создание access и refresh токенов
    public String createRefreshToken(UserEntity user, String ipAddress) {
        return generateToken(user, REFRESH_TIME, ipAddress, "refresh");
    }

    public String createAccessToken(UserEntity user, String ipAddress) {
        return generateToken(user, ACCESS_TIME, ipAddress, "access");
    }



    // * Валидация
    public boolean validateToken(String token) {
        try {
            extractAllClaims(token);
            return true;
        }
        // Токен истек - обновляем
        catch (ExpiredJwtException e) {
            return false;
        }
    }



    public Claims extractAllClaims(String token) {
        return Jwts.parserBuilder()
            .setSigningKey(key)
            .build()
            .parseClaimsJws(token)
            .getBody();
    }
}
