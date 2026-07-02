package com.golgan.task5.modules.users.controllers;

import com.golgan.task5.core.controllers.TemplateListController;
import com.golgan.task5.modules.users.models.UserEntity;
import com.golgan.task5.modules.users.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/users")
public class UserListController extends TemplateListController<UserEntity, Long> {

    protected UserListController(UserService service) {
        super(service);
    }

    @Override
    protected String getTemplateName() {
        return "users/list";
    }
}
