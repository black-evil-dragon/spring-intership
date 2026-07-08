package com.golgan.toduo.modules.users.controllers;

import com.golgan.toduo.modules.users.dto.*;
import com.golgan.toduo.modules.users.mappers.UserMapper;
import com.golgan.toduo.modules.users.models.UserEntity;
import com.golgan.toduo.modules.users.services.UserService;

import jakarta.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/users")
public class UserController {

    private final UserService service;
    private final UserMapper mapper;

    public UserController(UserService userService, UserMapper mapper) {
        this.service = userService;
        this.mapper = mapper;
    }


    // * ======================== READ ========================
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Page<UserSummaryDto> getAll(@PageableDefault(size = 20, sort = "id", direction = Sort.Direction.ASC) Pageable pageable) {
        Page<UserEntity> users = service.findAll(pageable);

        return users.map(mapper::toSummaryDto);
    }



    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public UserDetailDto getById(@PathVariable Long id) {
        UserEntity user = service.findById(id);

        return mapper.toDetailDto(user);
    }



    // * ======================== CREATE ========================
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserDetailDto create(@Valid @RequestBody UserCreateDto createDto) {
        UserEntity newUser = service.create(createDto);

        return mapper.toDetailDto(newUser);
    }



    // * ======================== UPDATE ========================
    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public UserDetailDto update(@PathVariable Long id, @Valid @RequestBody UserUpdateDto updateDto) {
        UserEntity user = service.update(id, updateDto);

        return mapper.toDetailDto(user);
    }



    // * ======================== DELETE ========================
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.deleteById(id);
    }
}
