package com.golgan.toduo.modules.tasks.repositories;

import com.golgan.toduo.modules.tasks.models.TaskEntity;
import com.golgan.toduo.modules.tasks.models.TaskStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TaskRepository extends JpaRepository<TaskEntity, Long> {

    Page<TaskEntity> findByStatus(TaskStatus status, Pageable pageable);

    Page<TaskEntity> findByColumnId(Long columnId, Pageable pageable);

    @EntityGraph(attributePaths = {"column", "column.desk"})
    Optional<TaskEntity> findWithDeskById(Long id);
}
