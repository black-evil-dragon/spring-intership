package com.golgan.task5.modules.tasks.controllers;

import com.golgan.task5.core.controllers.templates.TemplateCreateController;

import com.golgan.task5.modules.tasks.dto.TaskCreateData;
import com.golgan.task5.modules.tasks.models.TaskEntity;
import com.golgan.task5.modules.tasks.services.TaskService;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;

import org.springframework.web.bind.annotation.RequestMapping;



@Controller
@RequestMapping("/tasks")
public class TaskCreateController extends TemplateCreateController<TaskEntity, TaskCreateData, Long> {


    protected TaskCreateController(TaskService service) {
        super(service);
    }

    @Override
    protected void validate(TaskCreateData createData, BindingResult bindingResult) {
    }

    @Override
    protected String getTemplateName() {
        return "tasks/create";
    }


    @Override
    protected TaskCreateData createEmptyDto() {
        return new TaskCreateData();
    }


    @Override
    protected String getSuccessUrl() {
        return "/tasks";
    }



    @Override
    protected void createEntity(TaskCreateData createRequest) {
        TaskEntity entity = new TaskEntity();

        service.save(entity);
    }

}
