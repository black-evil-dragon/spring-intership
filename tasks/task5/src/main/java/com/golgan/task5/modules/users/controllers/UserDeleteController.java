package com.golgan.task5.modules.users.controllers;

import com.golgan.task5.core.controllers.templates.TemplateDeleteController;
import com.golgan.task5.modules.users.models.UserEntity;
import com.golgan.task5.modules.users.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/users")
public class UserDeleteController extends TemplateDeleteController<UserEntity, Long> {

    protected UserDeleteController(UserService service) {
        super(service);
    }

    @Override
    protected String getTemplateName() {
        return "users/delete";
    }

    @Override
    protected String getSuccessUrl() {
        return "/users";
    }
}
