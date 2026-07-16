package com.golgan.toduo.modules.auth.controllers;


import com.golgan.toduo.core.utils.HttpUtil;
import com.golgan.toduo.modules.auth.dto.*;
import com.golgan.toduo.modules.auth.services.AuthService;
import com.golgan.toduo.modules.auth.services.JwtService;
import com.golgan.toduo.modules.users.dto.UserCreateDto;
import com.golgan.toduo.modules.users.mappers.UserMapper;
import com.golgan.toduo.modules.users.models.UserEntity;
import com.golgan.toduo.modules.users.services.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;
    private final UserMapper userMapper;

    private final AuthService authService;
    private final JwtService jwtService;

    // Регистрация пользователя
    @PostMapping("/register")
    @ResponseStatus(HttpStatus.OK)
    public LoginResponseDto register(
        @Valid @RequestBody UserCreateDto userCreateDto,
        HttpServletRequest request
    ) {
        // Собираем информацию о пользователе и создаем его
        String userIP = HttpUtil.getClientIp(request);
        UserEntity user = userService.create(userCreateDto);

        TokenPairDto tokens = jwtService.generateTokenPair(user, userIP);

        return new LoginResponseDto(
            userMapper.toDetailDto(user),
            tokens.accessToken()
        );
    }

    // Вход пользователя
    // Оставляем пространство для альтернативной аутентификации
    @PostMapping("/login")
    @ResponseStatus(HttpStatus.OK)
    public LoginResponseDto login(
        @Valid @RequestBody LoginByEmailRequestDto loginRequest,
        HttpServletRequest request,
        HttpServletResponse response
    ) {
        // * Собираем информацию о пользователе
        UserEntity user = authService.authenticateByEmail(loginRequest);
        String userIP = HttpUtil.getClientIp(request);

        // * Создаем токены и устанавливаем их
        TokenPairDto tokens = jwtService.generateTokenPair(user, userIP);
        HttpUtil.setTokens(response, tokens);

        return new LoginResponseDto(
            userMapper.toDetailDto(user),
            tokens.accessToken()
        );
    }

    // Ротация токенов пользователя
    @PostMapping("/refresh")
    @ResponseStatus(HttpStatus.OK)
    public AccessTokenDto refresh(
        HttpServletRequest request,
        HttpServletResponse response
    ) {
        // * Собираем информацию о пользователе
        String userIP = HttpUtil.getClientIp(request);
        String refreshToken = HttpUtil.getRefreshTokenFromCookie(request.getCookies());

        // * Создаем токены и устанавливаем их
        TokenPairDto tokens = jwtService.updateTokens(refreshToken, userIP);
        HttpUtil.setTokens(response, tokens);

        return new AccessTokenDto(
            tokens.accessToken()
        );
    }

    // Выход пользователя
    @PostMapping("/logout")
    @ResponseStatus(HttpStatus.OK)
    public void logout(
        HttpServletRequest request,
        HttpServletResponse response
    ) {
        // * Собираем информацию о пользователе
        String oldRefreshToken = HttpUtil.getRefreshTokenFromCookie(request.getCookies());

        // * Работаем с бизнес логикой
        HttpUtil.deleteCookieRefreshToken(response);
        jwtService.logout(oldRefreshToken);
    }

}
