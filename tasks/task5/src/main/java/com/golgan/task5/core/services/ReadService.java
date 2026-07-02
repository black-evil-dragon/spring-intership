package com.golgan.task5.core.services;

import jakarta.persistence.EntityNotFoundException;

import java.util.List;
import java.util.Optional;

public interface ReadService<E, ID> extends BaseService<E, ID> {
    default E getById(ID id) {
        return getRepository().findById(id).orElse(null);
    }

    default E findById(ID id) {
        return getRepository().findById(id).orElseThrow(() -> new EntityNotFoundException("Сущность не найдена с id: " + id));
    }

    default List<E> findAll() {
        return getRepository().findAll();
    }


}
