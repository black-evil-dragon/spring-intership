package com.golgan.task5.modules.tasks.controllers;

import com.golgan.task5.core.controllers.templates.TemplateListController;

import com.golgan.task5.modules.tasks.models.TaskEntity;
import com.golgan.task5.modules.tasks.services.TaskService;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;


@Controller
@RequestMapping("/tasks")
public class TaskListController extends TemplateListController<TaskEntity, Long> {

    // Мне кажется тут что-то не так
    private final TaskService taskService;

    protected TaskListController(TaskService service) {
        super(service);
        this.taskService = service;
    }

    @Override
    protected String getTemplateName() {
        return "tasks/list";
    }


    @Override
    protected List<TaskEntity> getFilteredEntities(String query) {
        return taskService.searchTasks(query);
    }
}
