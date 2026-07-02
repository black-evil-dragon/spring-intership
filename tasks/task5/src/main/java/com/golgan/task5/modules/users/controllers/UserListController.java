package com.golgan.task5.modules.users.controllers;

import com.golgan.task5.core.controllers.templates.TemplateListController;
import com.golgan.task5.modules.users.models.UserEntity;
import com.golgan.task5.modules.users.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/users")
public class UserListController extends TemplateListController<UserEntity, Long> {

    private final UserService userService;

    protected UserListController(UserService service) {
        super(service);
        this.userService = service;
    }

    @Override
    protected String getTemplateName() {
        return "users/list";
    }


    @Override
    protected List<UserEntity> getFilteredEntities(String query) {
        return userService.getRepository().findAllByEmailContainingIgnoreCase(query);
    }
}
