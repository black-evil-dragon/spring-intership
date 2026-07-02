package com.golgan.task5.core.services;

import jakarta.transaction.Transactional;

/// # Сервис создания сущностей
///
/// @param <E> Сущность
/// @param <ID> Тип ключа сущности
public interface CreateService<E, ID> extends BaseService<E, ID> {

    @Transactional
    default E save(E entity) {
        return getRepository().save(entity);
    }


}
