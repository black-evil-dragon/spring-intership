package com.golgan.task5.modules.tasks.repositories;

import com.golgan.task5.modules.tasks.models.TaskEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<TaskEntity, Long> {
}
