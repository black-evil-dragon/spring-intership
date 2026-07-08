package com.golgan.toduo.modules.tasks.services;

import com.golgan.toduo.modules.tasks.dto.TaskCreateDto;
import com.golgan.toduo.modules.tasks.dto.TaskUpdateDto;
import com.golgan.toduo.modules.tasks.mappers.TaskMapper;
import com.golgan.toduo.modules.tasks.models.TaskEntity;
import com.golgan.toduo.modules.tasks.models.TaskStatus;
import com.golgan.toduo.modules.tasks.repositories.TaskRepository;

import com.golgan.toduo.modules.users.models.UserEntity;
import com.golgan.toduo.modules.users.services.UserService;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;



@Service
public class TaskService {

    private final UserService userService;
    private final TaskRepository repository;
    private final TaskMapper mapper;


    public TaskService(UserService userService, TaskRepository repository, TaskMapper mapper) {
        this.userService = userService;
        this.repository = repository;
        this.mapper = mapper;
    }


    // * ======================== READ ========================
    @Transactional(readOnly = true)
    public Page<TaskEntity> getAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Transactional(readOnly = true)
    public Page<TaskEntity> getFiltered(TaskStatus status, Pageable pageable) {
        return repository.findByStatus(status, pageable);
    }

    @Transactional(readOnly = true)
    public TaskEntity getById(Long id) {
        return getOrNotFound(repository.findById(id).orElse(null));
    }


    // * ======================== CREATE ========================
    @Transactional
    public TaskEntity create(TaskCreateDto createDto) {
        // Создание задачи
        TaskEntity newTask = mapper.toEntity(createDto);


        // Добавление Постановщика и Исполнителя
        setAuthorByUserId(createDto.authorId(), newTask);

        if (createDto.assigneeId() != null) {
            addAssigneeByUserId(createDto.authorId(), newTask);
        }


        return repository.save(newTask);
    }


    // * ======================== UPDATE ========================
    @Transactional
    public TaskEntity update(Long id, TaskUpdateDto updateDto) {
        // Получение задачи
        TaskEntity task = getOrNotFound(repository.findById(id).orElse(null));


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
    @Transactional
    public void delete(Long id) {
        TaskEntity Task = getOrNotFound(repository.findById(id).orElse(null));

        repository.delete(Task);
    }



    // * ======================== UTILS ========================
    public TaskEntity getOrNotFound(TaskEntity entity) {
        if (entity == null) {
            throw new ResponseStatusException(
                HttpStatus.NOT_FOUND, "Задача не найдена"
            );
        }
        return entity;
    }


    public void setAuthorByUserId(Long userId, TaskEntity task) {
        UserEntity user = userService.getOrNotFound(userService.getById(userId), "Постановщик");

        task.setAuthor(user);
    }

    public void addAssigneeByUserId(Long userId, TaskEntity task) {
        UserEntity user = userService.getOrNotFound(userService.getById(userId), "Исполнитель");

        task.setAssignee(user);
    }
}
