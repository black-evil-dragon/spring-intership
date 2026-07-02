package com.golgan.task5.modules.users.controllers;

import com.golgan.task5.core.controllers.templates.TemplateCreateController;
import com.golgan.task5.core.services.PasswordService;
import com.golgan.task5.modules.users.dto.UserCreateData;
import com.golgan.task5.modules.users.models.UserEntity;
import com.golgan.task5.modules.users.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/users")
public class UserCreateController extends TemplateCreateController<UserEntity, UserCreateData, Long> {

    private final PasswordService passwordService;
    private final UserService service;

    protected UserCreateController(UserService service, PasswordService passwordService, UserService service1) {
        super(service);
        this.passwordService = passwordService;
        this.service = service;
    }

    @Override
    protected void validate(UserCreateData createData, BindingResult bindingResult) {
        if (service.getRepository().existsByEmail(createData.email())) {
            bindingResult.addError(
                new FieldError("form",
                    "email",
                    "Пользователь с таким Email уже зарегистрирован"
                )
            );
        }
    }

    @Override
    protected String getTemplateName() {
        return "users/create";
    }

    @Override
    protected UserCreateData createEmptyDto() {
        return new UserCreateData("", "");
    }


    @Override
    protected String getSuccessUrl() {
        return "/users";
    }



    // User service logic
    @Override
    protected void createEntity(UserCreateData createRequest) {
        UserEntity entity = new UserEntity();
        entity.setEmail(createRequest.email());
        entity.setPassword(passwordService.encode(createRequest.password()));

        service.save(entity);
    }

}
