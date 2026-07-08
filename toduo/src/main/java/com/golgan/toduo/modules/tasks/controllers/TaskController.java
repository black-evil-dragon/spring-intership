package com.golgan.toduo.modules.tasks.controllers;

import com.golgan.toduo.modules.tasks.dto.TaskCreateDto;
import com.golgan.toduo.modules.tasks.dto.TaskDetailDto;
import com.golgan.toduo.modules.tasks.dto.TaskSummaryDto;
import com.golgan.toduo.modules.tasks.dto.TaskUpdateDto;
import com.golgan.toduo.modules.tasks.mappers.TaskMapper;
import com.golgan.toduo.modules.tasks.models.TaskEntity;
import com.golgan.toduo.modules.tasks.models.TaskStatus;
import com.golgan.toduo.modules.tasks.services.TaskService;

import com.golgan.toduo.modules.users.dto.UserSummaryDto;
import com.golgan.toduo.modules.users.mappers.UserMapper;
import com.golgan.toduo.modules.users.models.UserEntity;
import com.golgan.toduo.modules.users.services.UserService;
import jakarta.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Stream;


@RestController
@RequestMapping(value = "/api/v1/tasks")
public class TaskController {

    private final TaskService service;
    private final TaskMapper mapper;

    private final UserService userService;
    private final UserMapper userMapper;

    public TaskController(TaskService TaskService, UserService userService, TaskMapper mapper, UserMapper userMapper) {
        this.service = TaskService;
        this.userService = userService;
        this.mapper = mapper;
        this.userMapper = userMapper;
    }


    // * ======================== READ ========================
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public PagedModel<TaskSummaryDto> getAll(
        @RequestParam(required = false) String status,
        @PageableDefault(size = 20, sort = "id", direction = Sort.Direction.ASC) Pageable pageable
    ) {
        Page<TaskEntity> tasks;

        // Вообще интересно почитать про динамик sql, но пока не буду городить код этим
        if (status != null) {
            tasks = service.getFiltered(TaskStatus.valueOf(status.trim().toUpperCase()), pageable);
            return new PagedModel<>(tasks.map(mapper::toSummaryDto));
        }


        tasks = service.getAll(pageable);

        return new PagedModel<>(tasks.map(mapper::toSummaryDto));
    }


    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public TaskDetailDto getById(@PathVariable Long id) {
        TaskEntity task = service.getById(id);

        return mapper.toDetailDto(task);
    }

    @GetMapping("/{id}/users")
    @ResponseStatus(HttpStatus.OK)
    public List<UserSummaryDto> getUsersByTaskId(@PathVariable Long id) {
        TaskEntity task = service.getById(id);
        List<UserEntity> users = userService.findAllByTask(task);

        return users.stream().map(userMapper::toSummaryDto).toList();
    }


    // * ======================== CREATE ========================
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TaskDetailDto create(@Valid @RequestBody TaskCreateDto createDto) {
        TaskEntity newTask = service.create(createDto);

        return mapper.toDetailDto(newTask);
    }


    // * ======================== UPDATE ========================
    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public TaskDetailDto update(@PathVariable Long id, @Valid @RequestBody TaskUpdateDto updateDto) {
        TaskEntity task = service.update(id, updateDto);

        return mapper.toDetailDto(task);
    }


    // * ======================== DELETE ========================
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
