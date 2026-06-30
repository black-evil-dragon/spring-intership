package com.golgan.task5.core.services;

import org.springframework.data.jpa.repository.JpaRepository;

public interface BaseService<E, ID> {
    JpaRepository<E, ID> getRepository();
}
