package com.golgan.task5.modules.tasks.services;

import com.golgan.task5.core.services.CRUDService;
import com.golgan.task5.modules.tasks.models.TaskEntity;
import com.golgan.task5.modules.tasks.repositories.TaskRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class TaskService implements CRUDService<TaskEntity, Long> {

    private final TaskRepository repository;

    public TaskService(TaskRepository repository) {
        this.repository = repository;
    }

    @Override
    public JpaRepository<TaskEntity, Long> getRepository() {
        return repository;
    }
}
