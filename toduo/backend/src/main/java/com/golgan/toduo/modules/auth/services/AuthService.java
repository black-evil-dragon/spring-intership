package com.golgan.toduo.modules.auth.services;

import com.golgan.toduo.core.services.PasswordService;
import com.golgan.toduo.modules.auth.dto.LoginByEmailRequest;
import com.golgan.toduo.modules.auth.exceptions.AuthDataMissMatchException;
import com.golgan.toduo.modules.users.models.UserEntity;
import com.golgan.toduo.modules.users.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;




@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserService userService;
    private final PasswordService passwordService;

    public UserEntity authenticateByEmail(LoginByEmailRequest loginRequest) {
        UserEntity user = userService.getByEmail(loginRequest.email());


        if (!passwordService.matches(
            loginRequest.password(),
            user.getPassword())
        ){
            throw new AuthDataMissMatchException();
        }

        return user;
    }

}
