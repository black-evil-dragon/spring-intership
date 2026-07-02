package com.golgan.task5.modules.users.controllers;

import com.golgan.task5.core.controllers.templates.TemplateDetailController;
import com.golgan.task5.modules.tasks.dto.TaskCreateData;
import com.golgan.task5.modules.tasks.models.TaskEntity;
import com.golgan.task5.modules.tasks.services.TaskService;
import com.golgan.task5.modules.users.models.UserEntity;
import com.golgan.task5.modules.users.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/users")
public class UserDetailController extends TemplateDetailController<UserEntity, Long> {

    private final TaskService taskService;

    protected UserDetailController(UserService service, TaskService taskService) {
        super(service);
        this.taskService = taskService;
    }

    @Override
    protected String render(Model model, String templateName, UserEntity user) {
        List<TaskEntity> tasks = taskService.getRepository().findAllByUserId(user.getId());
        TaskCreateData taskCreateData = new TaskCreateData("", "", user.getId());

        model.addAttribute("form", taskCreateData);
        model.addAttribute("tasks", tasks);

        return super.render(model, templateName, user);
    }

    @Override
    protected String getTemplateName() {
        return "users/detail";
    }
}
