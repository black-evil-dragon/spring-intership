package com.golgan.task5.modules.tasks.controllers;

import com.golgan.task5.core.controllers.CRUDController;
import com.golgan.task5.modules.tasks.models.TaskEntity;
import com.golgan.task5.modules.tasks.services.TaskService;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/tasks")
public class TaskController extends CRUDController<TaskEntity, Long> {

    protected TaskController(TaskService service) {
        super(service);
    }
}
