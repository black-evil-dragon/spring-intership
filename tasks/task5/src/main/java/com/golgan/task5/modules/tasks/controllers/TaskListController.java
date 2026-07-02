package com.golgan.task5.modules.tasks.controllers;

import com.golgan.task5.core.controllers.templates.TemplateListController;

import com.golgan.task5.modules.tasks.models.TaskEntity;
import com.golgan.task5.modules.tasks.services.TaskService;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;



@Controller
@RequestMapping("/tasks")
public class TaskListController extends TemplateListController<TaskEntity, Long> {

    protected TaskListController(TaskService service) {
        super(service);
    }

    @Override
    protected String getTemplateName() {
        return "tasks/list";
    }
}
