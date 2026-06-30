package com.golgan.task5.core.services;

import org.springframework.data.jpa.repository.JpaRepository;

/// # Сервис создания сущностей
///
/// @param <E> Сущность
/// @param <ID> Тип ключа сущности
public interface CreateService<E, ID> extends BaseService<E, ID> {

    ///
    default E save(E entity) {
        return getRepository().save(entity);
    }


}
