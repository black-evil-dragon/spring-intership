package com.golgan.toduo.modules.desks.controllers;


import com.golgan.toduo.modules.desks.dto.DeskColumnCreateDto;
import com.golgan.toduo.modules.desks.dto.DeskColumnDetailDto;
import com.golgan.toduo.modules.desks.dto.DeskColumnSummaryDto;
import com.golgan.toduo.modules.desks.dto.DeskColumnUpdateDto;
import com.golgan.toduo.modules.desks.mappers.DeskColumnMapper;
import com.golgan.toduo.modules.desks.models.DeskColumnEntity;
import com.golgan.toduo.modules.desks.services.DeskColumnService;
import com.golgan.toduo.modules.tasks.dto.TaskSummaryDto;
import com.golgan.toduo.modules.tasks.mappers.TaskMapper;
import com.golgan.toduo.modules.tasks.models.TaskEntity;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping(value = "/api/v1/desks/{deskId}/columns")
@RequiredArgsConstructor
public class DeskColumnsController {

    private final DeskColumnService deskColumnService;
    private final DeskColumnMapper deskColumnMapper;

    private final TaskMapper taskMapper;

    // * ======================== READ ========================
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<DeskColumnSummaryDto> getAll(
        @PathVariable Long deskId) {

        List<DeskColumnEntity> columns = deskColumnService.findAll(deskId);


        return new ArrayList<>(
            columns.stream()
                .map(deskColumnMapper::toSummaryDto)
                .toList()
        );
    }


    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public DeskColumnDetailDto getById(@PathVariable Long deskId, @PathVariable Long id) {
        DeskColumnEntity column = deskColumnService.findById(deskId, id);

        return deskColumnMapper.toDetailDto(column);
    }


    @GetMapping("/{id}/tasks")
    @ResponseStatus(HttpStatus.OK)
    public PagedModel<TaskSummaryDto> getTasksById(@PathVariable Long deskId, @PathVariable Long id, @PageableDefault(size = 20, sort = "id", direction = Sort.Direction.ASC) Pageable pageable) {
        Page<TaskEntity> tasks = deskColumnService.getTasksByColumnId(deskId, id, pageable);

        return new PagedModel<>(tasks.map(taskMapper::toSummaryDto));
    }


    // * ======================== CREATE ========================
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public DeskColumnDetailDto create(@PathVariable Long deskId, @Valid @RequestBody DeskColumnCreateDto createDto) {
        DeskColumnEntity column = deskColumnService.create(deskId, createDto);

        return deskColumnMapper.toDetailDto(column);
    }


    // * ======================== UPDATE ========================
    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public DeskColumnDetailDto update(@PathVariable Long deskId, @PathVariable Long id, @Valid @RequestBody DeskColumnUpdateDto updateDto) {
        DeskColumnEntity column = deskColumnService.update(deskId, id, updateDto);

        return deskColumnMapper.toDetailDto(column);
    }


    // * ======================== DELETE ========================
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long deskId, @PathVariable Long id) {
        deskColumnService.delete(deskId, id);

    }
}
