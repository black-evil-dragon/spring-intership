package com.golgan.toduo.modules.desks.controllers;


import com.golgan.toduo.modules.desks.dto.DeskCreateDto;
import com.golgan.toduo.modules.desks.dto.DeskDetailDto;
import com.golgan.toduo.modules.desks.dto.DeskSummaryDto;
import com.golgan.toduo.modules.desks.dto.DeskUpdateDto;
import com.golgan.toduo.modules.desks.mappers.DeskMapper;
import com.golgan.toduo.modules.desks.models.DeskEntity;
import com.golgan.toduo.modules.desks.services.DeskService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(value = "/api/v1/desks")
@RequiredArgsConstructor
public class DeskController {

    private final DeskService deskService;
    private final DeskMapper deskMapper;



    // * ======================== READ ========================
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public PagedModel<DeskSummaryDto> getAll(
        @PageableDefault(size = 20, sort = "id", direction = Sort.Direction.ASC) Pageable pageable
    ) {

        Page<DeskEntity> desks = deskService.findAll(pageable);

        return new PagedModel<>(desks.map(deskMapper::toSummaryDto));
    }


    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public DeskDetailDto getById(@PathVariable Long id) {
        DeskEntity desk = deskService.findById(id);

        return deskMapper.toDetailDto(desk);
    }



    // * ======================== CREATE ========================
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public DeskDetailDto create(@Valid @RequestBody DeskCreateDto createDto) {
        DeskEntity desk = deskService.create(createDto);

        return deskMapper.toDetailDto(desk);
    }


    // * ======================== UPDATE ========================
    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public DeskDetailDto update(@PathVariable Long id, @Valid @RequestBody DeskUpdateDto updateDto) {
        DeskEntity desk = deskService.update(id, updateDto);

        return deskMapper.toDetailDto(desk);
    }


    // * ======================== DELETE ========================
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        deskService.deleteById(id);
    }
}
