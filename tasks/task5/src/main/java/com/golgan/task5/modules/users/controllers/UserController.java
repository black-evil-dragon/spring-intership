package com.golgan.task5.modules.users.controllers;

import com.golgan.task5.core.controllers.CRUDController;
import com.golgan.task5.modules.users.models.UserEntity;
import com.golgan.task5.modules.users.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/users")
public class UserController extends CRUDController<UserEntity, Long> {

    protected UserController(UserService service) {
        super(service);
    }
}
