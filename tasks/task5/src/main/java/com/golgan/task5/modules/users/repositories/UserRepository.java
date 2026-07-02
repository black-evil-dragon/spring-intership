package com.golgan.task5.modules.users.repositories;

import com.golgan.task5.modules.users.models.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    boolean existsByEmail(String email);
}
