package com.golgan.task5.modules.users.controllers;

import com.golgan.task5.core.controllers.TemplateDetailController;
import com.golgan.task5.modules.users.models.UserEntity;
import com.golgan.task5.modules.users.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/users")
public class UserDetailController extends TemplateDetailController<UserEntity, Long> {

    protected UserDetailController(UserService service) {
        super(service);
    }

    @Override
    protected String getTemplateName() {
        return "users/detail";
    }
}
