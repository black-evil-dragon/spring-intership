package com.golgan.toduo.modules.tasks.services;

import com.golgan.toduo.modules.tasks.dto.TaskCreateDto;
import com.golgan.toduo.modules.tasks.dto.TaskUpdateDto;
import com.golgan.toduo.modules.tasks.mappers.TaskMapper;
import com.golgan.toduo.modules.tasks.models.TaskEntity;
import com.golgan.toduo.modules.tasks.repositories.TaskRepository;

import com.golgan.toduo.modules.users.models.UserEntity;
import com.golgan.toduo.modules.users.repositories.UserRepository;
import com.golgan.toduo.modules.users.services.UserService;
import jakarta.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
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
    @Transactional
    public Page<TaskEntity> getAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Transactional
    public TaskEntity getById(Long id) {
        return getOrNotFound(repository.findById(id).orElse(null));
    }


    // * ======================== CREATE ========================
    @Transactional
    public TaskEntity create(TaskCreateDto createDto) {
        if (userService.getById(createDto.authorId()) == null) {
            throw new ResponseStatusException(
                HttpStatus.BAD_REQUEST, "Постановщика с заданным ID не существует"
            );
        }

        UserEntity author = userService.getById(createDto.authorId());
        TaskEntity newTask = mapper.toEntity(createDto);

        newTask.setAuthor(author);


        return repository.save(newTask);
    }


    // * ======================== UPDATE ========================
    @Transactional
    public TaskEntity update(Long id, TaskUpdateDto updateDto) {
        TaskEntity Task = getOrNotFound(repository.findById(id).orElse(null));

        mapper.update(updateDto, Task);

        return repository.save(Task);
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
}
