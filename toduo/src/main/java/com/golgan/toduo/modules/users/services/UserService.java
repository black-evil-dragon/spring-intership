package com.golgan.toduo.modules.users.services;

import com.golgan.toduo.core.services.PasswordService;
import com.golgan.toduo.modules.users.dto.UserCreateDto;
import com.golgan.toduo.modules.users.dto.UserUpdateDto;
import com.golgan.toduo.modules.users.exceptions.EmailAlreadyExistException;
import com.golgan.toduo.modules.users.exceptions.UserNotFoundException;
import com.golgan.toduo.modules.users.mappers.UserMapper;
import com.golgan.toduo.modules.users.models.UserEntity;
import com.golgan.toduo.modules.users.repositories.UserRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;


@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository repository;
    private final UserMapper mapper;
    private final PasswordService passwordService;


    // * ======================== READ ========================
    @Transactional(readOnly = true)
    public UserEntity getByEmail(String email) {
        return getUserOrNotFound(repository.findByEmail(email));
    }


    @Transactional(readOnly = true)
    public UserEntity findById(Long id) {
        return getUserOrNotFound(id);
    }

    @Transactional(readOnly = true)
    public Page<UserEntity> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Transactional(readOnly = true)
    public List<UserEntity> findAll() {
        return repository.findAll();
    }


    // * ======================== CREATE ========================
    @Transactional
    public UserEntity create(@Valid @RequestBody UserCreateDto createDto) {
        if (repository.existsByEmail(createDto.email())) {
            throw new EmailAlreadyExistException();
        }
        UserEntity newUser = mapper.toEntity(createDto);

        newUser.setPassword(
            passwordService.encode(createDto.password())
        );

        return repository.save(newUser);
    }


    // * ======================== UPDATE ========================
    @Transactional
    public UserEntity update(Long id, @Valid @RequestBody UserUpdateDto updateDto) {
        UserEntity user = getUserOrNotFound(id);

        mapper.update(updateDto, user);

        return repository.save(user);
    }



    // * ======================== UTILS ========================
    @Transactional
    public void deleteById(Long id) {
        UserEntity entity = getUserOrNotFound(id);

        repository.delete(entity);
    }



    // * ======================== UTILS ========================
    public UserEntity getUserOrNotFound(UserEntity entity) {
        if (entity == null) {
            throw new UserNotFoundException();
        }
        return entity;
    }

    public UserEntity getUserOrNotFound(Long id) {
        UserEntity entity = repository.findById(id).orElse(null);

        return getUserOrNotFound(entity);
    }
}
