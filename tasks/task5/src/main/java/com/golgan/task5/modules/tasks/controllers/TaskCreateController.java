package com.golgan.task5.modules.tasks.controllers;

import com.golgan.task5.core.controllers.templates.TemplateCreateController;

import com.golgan.task5.modules.tasks.dto.TaskCreateData;
import com.golgan.task5.modules.tasks.models.TaskEntity;
import com.golgan.task5.modules.tasks.services.TaskService;

import com.golgan.task5.modules.users.models.UserEntity;
import com.golgan.task5.modules.users.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;


@Controller
@RequestMapping("/tasks")
public class TaskCreateController extends TemplateCreateController<TaskEntity, TaskCreateData, Long> {

    private final UserService userService;

    protected TaskCreateController(TaskService service, UserService userService) {
        super(service);
        this.userService = userService;
    }


    @Override
    protected void initModel(Model model) {
        List<UserEntity> users = userService.findAll();
        model.addAttribute("users", users);

        super.initModel(model);
    }

    @Override
    protected void validate(TaskCreateData createData, BindingResult bindingResult) {
        if (createData.userId() == null) {
            // Можно самому указать ошибку, но на дто установлен not null
            return;
        }

        if (!userService.getRepository().existsById(createData.userId())) {
            bindingResult.addError(
                new FieldError("form",
                    "userId",
                    "Пользователь не найден"
                )
            );
        }
    }


    @Override
    protected String getTemplateName() {
        return "tasks/create";
    }


    @Override
    protected TaskCreateData createEmptyDto() {
        return new TaskCreateData("", "", null);
    }


    @Override
    protected String getSuccessUrl() {
        return "/tasks";
    }



    @Override
    protected void createEntity(TaskCreateData createRequest) {
        TaskEntity entity = new TaskEntity();

        entity.setTitle(createRequest.title());
        entity.setText(createRequest.text());
        entity.setUser(userService.findById(createRequest.userId()));

        service.save(entity);
    }

}
