package com.golgan.toduo.core.services;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;


import java.util.List;


// Возможно стоит сделать что-то типа ReadService и DeleteService, тк Create Update зачастую у всех по-разному проявляется
// А может не устраивать какашняк и убрать это :/

/// # Базовый сервис для чтения и удаления записей
///
/// @param <E> Сущность
/// @param <R> Репозиторий сущности
/// @param <ID> Идентификатор сущности
public abstract class CRUDService<E, R extends JpaRepository<E, ID>, ID> {

    protected final R repository;

    protected CRUDService(R repository) {
        this.repository = repository;
    }


    // * ======================== READ ========================
    @Transactional(readOnly = true)
    public E findById(ID id) {
        return getOrNotFound(repository.findById(id).orElse(null));
    }

    @Transactional(readOnly = true)
    public List<E> findAll() {
        return repository.findAll();
    }

    @Transactional(readOnly = true)
    public Page<E> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }


    // * ======================== CREATE ========================
    // @Transactional
    // protected abstract E create();


    // * ======================== UPDATE ========================
    // @Transactional
    // protected abstract E update();


    // * ======================== DELETE ========================
    @Transactional
    public void deleteById(ID id) {
        E entity = getOrNotFound(repository.findById(id).orElse(null));

        repository.delete(entity);
    }


    // * ======================== UTILS ========================
    // В целом это временные меры, можно написать свои exception-ы на каждый случай, в которых
    // будет более лаконично все расписано. Предполагаю
    /// Проверить сущность или выкинуть HTTP NOT_FOUND
    /// @param entity Сущность
    /// @param entityName Имя сущности
    public E getOrNotFound(E entity, String entityName) {
        if (entity == null) {
            throw new ResponseStatusException(
                HttpStatus.NOT_FOUND, entityName + " не найден"
            );
        }
        return entity;
    }

    /// Проверить сущность или выкинуть HTTP NOT_FOUND
    /// @param entity Сущность
    public E getOrNotFound(E entity) {
        return getOrNotFound(entity, "Объект");
    }


    /// Получить сущность пo ID или выкинуть HTTP NOT_FOUND
    /// @param id Идентификатор сущности
    /// @param entityName Имя сущности
    public E getByIdOrNotFound(ID id, String entityName) {
        E entity = repository.findById(id).orElse(null);

        return getOrNotFound(entity, entityName);
    }

    /// Получить сущность пo ID или выкинуть HTTP NOT_FOUND
    /// @param id Идентификатор сущности
    public E getByIdOrNotFound(ID id) {
        return getByIdOrNotFound(id, "Объект");
    }
}
