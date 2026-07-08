package com.golgan.toduo.modules.tasks.controllers;

import com.golgan.toduo.modules.tasks.dto.TaskCreateDto;
import com.golgan.toduo.modules.tasks.dto.TaskDetailDto;
import com.golgan.toduo.modules.tasks.dto.TaskSummaryDto;
import com.golgan.toduo.modules.tasks.dto.TaskUpdateDto;
import com.golgan.toduo.modules.tasks.mappers.TaskMapper;
import com.golgan.toduo.modules.tasks.models.TaskEntity;
import com.golgan.toduo.modules.tasks.services.TaskService;

import jakarta.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping(value = "/api/v1/tasks")
public class TaskController {

    private final TaskService service;
    private final TaskMapper mapper;

    public TaskController(TaskService TaskService, TaskMapper mapper) {
        this.service = TaskService;
        this.mapper = mapper;
    }


    // * ======================== READ ========================
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Page<TaskSummaryDto> getAll(@PageableDefault(size = 20, sort = "id", direction = Sort.Direction.ASC) Pageable pageable) {
        Page<TaskEntity> Tasks = service.getAll(pageable);

        return Tasks.map(mapper::toSummaryDto);
    }



    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public TaskDetailDto getById(@PathVariable Long id) {
        TaskEntity Task = service.getById(id);

        return mapper.toDetailDto(Task);
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
        TaskEntity Task = service.update(id, updateDto);

        return mapper.toDetailDto(Task);
    }



    // * ======================== DELETE ========================
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
