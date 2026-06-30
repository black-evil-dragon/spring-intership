package com.golgan.task5.core.services;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/// # Сервис создания сущностей
///
/// @param <E> Сущность
/// @param <ID> Тип ключа сущности
public interface DeleteService<E, ID> extends BaseService<E, ID> {

    /// ## Удаление по ID
    /// @param id ID сущности
    default void delete(ID id) {
        if (!getRepository().existsById(id)) {
            throw new EntityNotFoundException(
                "Невозможно удалить. Сущность не найдена с id: " + id
            );
        }
        getRepository().deleteById(id);
    }

    // Пока предполагается, что мы передаем список существующих ID
    default void deleteAllById(List<ID> ids) {
        for (ID id : ids) {
            delete(id);
        }
    }

}
