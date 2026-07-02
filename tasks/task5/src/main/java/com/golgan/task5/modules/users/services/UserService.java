package com.golgan.task5.modules.users.services;

import com.golgan.task5.core.services.CRUDService;
import com.golgan.task5.modules.users.models.UserEntity;
import com.golgan.task5.modules.users.repositories.UserRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService implements CRUDService<UserEntity, Long> {

    private final UserRepository repository;

    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public UserRepository getRepository() {
        return repository;
    }
}
