package com.golgan.toduo.modules.desks.controllers;


import com.golgan.toduo.modules.desks.dto.*;
import com.golgan.toduo.modules.desks.mappers.DeskColumnMapper;
import com.golgan.toduo.modules.desks.models.DeskColumnEntity;
import com.golgan.toduo.modules.desks.services.DeskColumnService;

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
import java.util.stream.Stream;


@RestController
@RequestMapping(value = "/api/v1/desks/{deskId}/columns")
@RequiredArgsConstructor
public class DeskColumnsController {

    private final DeskColumnService deskColumnService;
    private final DeskColumnMapper deskColumnMapper;


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
