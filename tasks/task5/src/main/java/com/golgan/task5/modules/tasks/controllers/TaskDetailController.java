package com.golgan.task5.modules.tasks.controllers;

import com.golgan.task5.core.controllers.templates.TemplateDetailController;

import com.golgan.task5.modules.tasks.models.TaskEntity;
import com.golgan.task5.modules.tasks.services.TaskService;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;



@Controller
@RequestMapping("/tasks")
public class TaskDetailController extends TemplateDetailController<TaskEntity, Long> {

    protected TaskDetailController(TaskService service) {
        super(service);
    }

    @Override
    protected String getTemplateName() {
        return "tasks/detail";
    }
}
