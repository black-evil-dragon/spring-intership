package com.golgan.toduo.modules.users.services;

import com.golgan.toduo.core.services.PasswordService;
import com.golgan.toduo.modules.users.dto.UserCreateDto;
import com.golgan.toduo.modules.users.dto.UserUpdateDto;
import com.golgan.toduo.modules.users.mappers.UserMapper;
import com.golgan.toduo.modules.users.models.UserEntity;
import com.golgan.toduo.modules.users.repositories.UserRepository;


import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class UserService {

    private final UserRepository repository;
    private final UserMapper mapper;

    private final PasswordService passwordService;

    public UserService(UserRepository repository, UserMapper mapper, PasswordService passwordService) {
        this.repository = repository;
        this.mapper = mapper;
        this.passwordService = passwordService;
    }


    // * ======================== READ ========================
    @Transactional
    public Page<UserEntity> getAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Transactional
    public UserEntity getById(Long id) {
        return getOrNotFound(repository.findById(id).orElse(null));
    }


    // * ======================== CREATE ========================
    @Transactional
    public UserEntity create(UserCreateDto createDto) {
        if (repository.existsByEmail(createDto.email())) {
            throw new ResponseStatusException(
                HttpStatus.BAD_REQUEST, "Пользователь уже существует"
            );
        }
        UserEntity newUser = mapper.toEntity(createDto);

        newUser.setPassword(
            passwordService.encode(createDto.password())
        );

        return repository.save(newUser);
    }


    // * ======================== UPDATE ========================
    @Transactional
    public UserEntity update(Long id, UserUpdateDto updateDto) {
        UserEntity user = getOrNotFound(repository.findById(id).orElse(null));

        mapper.update(updateDto, user);

        return repository.save(user);
    }


    // * ======================== DELETE ========================
    @Transactional
    public void delete(Long id) {
        UserEntity user = getOrNotFound(repository.findById(id).orElse(null));

        repository.delete(user);
    }



    // * ======================== UTILS ========================
    public UserEntity getOrNotFound(UserEntity entity) {
        if (entity == null) {
            throw new ResponseStatusException(
                HttpStatus.NOT_FOUND, "Пользователь не найден"
            );
        }
        return entity;


    }
}
