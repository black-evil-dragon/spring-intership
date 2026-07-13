package com.golgan.toduo.modules.tasks.services;

import com.golgan.toduo.modules.desks.services.DeskColumnService;
import com.golgan.toduo.modules.tasks.dto.TaskCreateDto;
import com.golgan.toduo.modules.tasks.dto.TaskUpdateDto;
import com.golgan.toduo.modules.tasks.exceptions.TaskNotFoundException;
import com.golgan.toduo.modules.tasks.mappers.TaskMapper;
import com.golgan.toduo.modules.tasks.models.TaskEntity;
import com.golgan.toduo.modules.tasks.models.TaskStatus;
import com.golgan.toduo.modules.tasks.repositories.TaskRepository;
import com.golgan.toduo.modules.users.models.UserEntity;
import com.golgan.toduo.modules.users.services.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;


@Service
@RequiredArgsConstructor
public class TaskService {


    private final TaskRepository repository;
    private final TaskMapper mapper;

    private final UserService userService;

    private final DeskColumnService deskColumnService;



    // * ======================== READ ========================
    @Transactional(readOnly = true)
    public TaskEntity findById(Long id) {
        return getTaskOrNotFound(id);
    }

    @Transactional(readOnly = true)
    public Page<TaskEntity> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Transactional(readOnly = true)
    public Page<TaskEntity> getFiltered(TaskStatus status, Pageable pageable) {
        return repository.findByStatus(status, pageable);
    }


    @Transactional(readOnly = true)
    public List<UserEntity> findUsersByTask(TaskEntity task) {
        return Stream.of(task.getAuthor(), task.getAssignee())
            .filter(Objects::nonNull)
            .distinct()
            .toList();
    }



    // * ======================== CREATE ========================
    @Transactional
    public TaskEntity create(@Valid @RequestBody TaskCreateDto createDto) {
        // Создание задачи
        TaskEntity newTask = mapper.toEntity(createDto);


        // Добавление Постановщика и Исполнителя
        setAuthorByUserId(createDto.authorId(), newTask);

        if (createDto.assigneeId() != null) {
            addAssigneeByUserId(createDto.assigneeId(), newTask);
        }

        deskColumnService.addTask(newTask, createDto.deskId(), createDto.columnId());


        return repository.save(newTask);
    }


    // * ======================== UPDATE ========================
    @Transactional
    public TaskEntity update(Long id, @Valid @RequestBody TaskUpdateDto updateDto) {
        // Получение задачи
        TaskEntity task = getTaskOrNotFound(id);


        // Обновление совподающих полей
        mapper.update(updateDto, task);


        // Обработка вставки Постановщика и Исполнителя
        if (updateDto.authorId() != null) {
            setAuthorByUserId(updateDto.authorId(), task);
        }

        if (updateDto.assigneeId() != null) {
            addAssigneeByUserId(updateDto.assigneeId(), task);
        }


        return repository.save(task);
    }

    // * ======================== DELETE ========================
    public void deleteById(Long id) {
        TaskEntity task = getTaskOrNotFound(id);

        repository.delete(task);
    }


    // * ======================== UTILS ========================

    public void setAuthorByUserId(Long userId, TaskEntity task) {
        UserEntity user = userService.getUserOrNotFound(userId);

        task.setAuthor(user);
    }

    public void addAssigneeByUserId(Long userId, TaskEntity task) {
        UserEntity user = userService.getUserOrNotFound(userId);

        task.setAssignee(user);
    }


    public TaskEntity getTaskOrNotFound(TaskEntity entity) {
        if (entity == null) {
            throw new TaskNotFoundException();
        }
        return entity;
    }

    public TaskEntity getTaskOrNotFound(Long id) {
        TaskEntity entity = repository.findWithDeskById(id).orElse(null);

        return getTaskOrNotFound(entity);
    }



}
