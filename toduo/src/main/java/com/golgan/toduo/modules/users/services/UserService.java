package com.golgan.toduo.modules.users.services;

import com.golgan.toduo.core.services.CRUDService;
import com.golgan.toduo.core.services.PasswordService;
import com.golgan.toduo.modules.tasks.models.TaskEntity;
import com.golgan.toduo.modules.users.dto.UserCreateDto;
import com.golgan.toduo.modules.users.dto.UserUpdateDto;
import com.golgan.toduo.modules.users.mappers.UserMapper;
import com.golgan.toduo.modules.users.models.UserEntity;
import com.golgan.toduo.modules.users.repositories.UserRepository;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;



@Service
public class UserService extends CRUDService<UserEntity, UserRepository, Long> {

    private final UserMapper mapper;

    private final PasswordService passwordService;

    public UserService(UserRepository repository, UserMapper mapper, PasswordService passwordService) {
        super(repository);

        this.mapper = mapper;
        this.passwordService = passwordService;
    }

    // * ======================== READ ========================
    @Transactional(readOnly = true)
    public UserEntity getByEmail(String email) {
        return getOrNotFound(repository.findByEmail(email));
    }


    @Transactional(readOnly = true)
    public List<UserEntity> findAllByTask(TaskEntity task) {
        return Stream.of(task.getAuthor(), task.getAssignee())
            .filter(Objects::nonNull)
            .distinct()
            .toList();
    }


    // * ======================== CREATE ========================
    @Transactional
    public UserEntity create(@Valid @RequestBody UserCreateDto createDto) {
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
    public UserEntity update(Long id, @Valid @RequestBody UserUpdateDto updateDto) {
        UserEntity user = getOrNotFound(repository.findById(id).orElse(null));

        mapper.update(updateDto, user);

        return repository.save(user);
    }
}
