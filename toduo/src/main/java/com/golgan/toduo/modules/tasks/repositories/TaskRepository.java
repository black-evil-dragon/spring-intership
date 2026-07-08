package com.golgan.toduo.modules.tasks.repositories;

import com.golgan.toduo.modules.tasks.models.TaskEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<TaskEntity, Long> {
}
