package com.golgan.toduo.modules.auth.controllers;


import com.golgan.toduo.modules.auth.dto.AccessTokenDto;
import com.golgan.toduo.modules.auth.dto.LoginByEmailRequest;
import com.golgan.toduo.modules.auth.dto.LoginResponse;
import com.golgan.toduo.modules.auth.dto.RegisterResponse;
import com.golgan.toduo.modules.auth.services.AuthService;
import com.golgan.toduo.modules.users.dto.UserCreateDto;
import com.golgan.toduo.modules.users.models.UserEntity;
import com.golgan.toduo.modules.users.services.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;
    private final AuthService authService;

    // Вход пользователя
    @PostMapping("/register")
    @ResponseStatus(HttpStatus.OK)
    public RegisterResponse register(@Valid @RequestBody UserCreateDto userCreateDto) {
        UserEntity user = userService.create(userCreateDto);

        return new RegisterResponse();
    }

    // Вход пользователя
    // Оставляем пространство для альтернативной аутентификации
    @PostMapping("/login")
    @ResponseStatus(HttpStatus.OK)
    public LoginResponse login(@Valid @RequestBody LoginByEmailRequest loginRequest) {
        UserEntity user = authService.authenticateByEmail(loginRequest);


        return new LoginResponse();
    }

    @PostMapping("/refresh")
    @ResponseStatus(HttpStatus.OK)
    public AccessTokenDto refresh() {
        return new AccessTokenDto("");
    }

    // @PostMapping("/verify")
    // @ResponseStatus(HttpStatus.OK)
    // public boolean verify() {
    //
    // }


    @PostMapping("/logout")
    @ResponseStatus(HttpStatus.OK)
    public void logout() {

    }

}
