package com.golgan.task5.modules.tasks.controllers;

import com.golgan.task5.core.controllers.templates.TemplateDeleteController;

import com.golgan.task5.modules.tasks.models.TaskEntity;
import com.golgan.task5.modules.tasks.services.TaskService;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;



@Controller
@RequestMapping("/tasks")
public class TaskDeleteController extends TemplateDeleteController<TaskEntity, Long> {

    protected TaskDeleteController(TaskService service) {
        super(service);
    }

    @Override
    protected String getTemplateName() {
        return "tasks/delete";
    }

    @Override
    protected String getSuccessUrl() {
        return "/tasks";
    }
}
