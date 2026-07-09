package com.golgan.toduo.modules.desks.services;


import com.golgan.toduo.modules.desks.dto.DeskColumnCreateDto;
import com.golgan.toduo.modules.desks.dto.DeskColumnUpdateDto;
import com.golgan.toduo.modules.desks.mappers.DeskColumnMapper;
import com.golgan.toduo.modules.desks.models.DeskColumnEntity;
import com.golgan.toduo.modules.desks.models.DeskEntity;
import com.golgan.toduo.modules.desks.repositories.DeskColumnRepository;
import com.golgan.toduo.modules.tasks.dto.TaskSummaryDto;
import com.golgan.toduo.modules.tasks.models.TaskEntity;
import com.golgan.toduo.modules.tasks.repositories.TaskRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;


@Service
@RequiredArgsConstructor
public class DeskColumnService {

    private final DeskService deskService;
    private final DeskColumnMapper mapper;
    private final DeskColumnRepository repository;

    private final TaskRepository taskRepository;


    // * ======================== READ ========================
    @Transactional(readOnly = true)
    public DeskColumnEntity findById(Long deskId, Long id) {
        DeskColumnEntity column = getByIdOrNotFound(id);

        checkColumnByDeskId(column, deskId);


        return column;
    }

    @Transactional(readOnly = true)
    public Page<DeskColumnEntity> findAll(Long deskId, Pageable pageable) {

        return repository.findAllByDeskIdOrderByPositionAsc(deskId, pageable);
    }

    @Transactional(readOnly = true)
    public List<DeskColumnEntity> findAll(Long deskId) {

        return repository.findAllByDeskIdOrderByPositionAsc(deskId);
    }


    @Transactional(readOnly = true)
    public Page<TaskEntity> getTasksByColumnId(Long deskId, Long columnId, Pageable pageable) {
        DeskColumnEntity column = getByIdOrNotFound(columnId);
        checkColumnByDeskId(column, deskId);


        return taskRepository.findByColumnId(columnId, pageable);
    }




    // * ======================== CREATE ========================
    @Transactional
    public DeskColumnEntity create(Long deskId, DeskColumnCreateDto createDto) {
        DeskEntity desk = deskService.findById(deskId);
        DeskColumnEntity column = mapper.toEntity(createDto);

        int maxPosition = repository.countByDeskId(deskId);

        column.setDesk(desk);
        column.setPosition(maxPosition + 1);

        return repository.save(column);
    }


    // * ======================== UPDATE ========================
    @Transactional
    public DeskColumnEntity update(Long deskId, Long id, @Valid DeskColumnUpdateDto updateDto) {
        DeskColumnEntity column = getByIdOrNotFound(id);

        checkColumnByDeskId(column, deskId);

        mapper.update(updateDto, column);

        if (updateDto.newPosition() != null) {
            moveColumn(column, updateDto.newPosition());
        }


        return repository.save(column);
    }


    // TODO Надо бы логику перемещения в sql написать
    @Transactional
    public void moveColumn(DeskColumnEntity column, Integer newPosition) {
        Long deskId = column.getDesk().getId();

        List<DeskColumnEntity> columns = new ArrayList<>(repository.findAllByDeskIdOrderByPositionAsc(deskId));

        if (newPosition < 0) {
            newPosition = 1;
        }

        if (newPosition > columns.size()) {
            newPosition = columns.size();
        }

        int targetIndex = newPosition - 1;

        int oldIndex = -1;
        for (int i = 0; i < columns.size(); i++) {
            if (columns.get(i).getId().equals(column.getId())) {
                oldIndex = i;
                break;
            }
        }


        if (oldIndex == -1 || oldIndex == targetIndex) {
            return;
        }

        columns.remove(oldIndex);
        columns.add(targetIndex, column);

        for (int i = 0; i < columns.size(); i++) {
            columns.get(i).setPosition(i + 1);
        }

        // Сохраняем измененния
        repository.saveAll(columns);
    }


    // * ======================== DELETE ========================
    // TODO Надо бы логику перемещения в sql написать
    @Transactional
    public void delete(Long deskId, Long id) {
        DeskColumnEntity column = getByIdOrNotFound(id);

        checkColumnByDeskId(column, deskId);

        // Удаляем колонку
        repository.delete(column);

        // Переопределяем индексы
        List<DeskColumnEntity> columns = repository.findAllByDeskIdOrderByPositionAsc(deskId);

        for (int i = 0; i < columns.size(); i++) {
            columns.get(i).setPosition(i);
        }

        repository.saveAll(columns);
    }

    // * ======================== UTILS ========================
    public void addTask(TaskEntity task, Long deskId, Long columnId) {
        DeskEntity desk = deskService.findById(deskId);

        DeskColumnEntity column;

        // Если не передан ID колонки
        if (columnId == null) {

            column = repository.findFirstByDeskIdOrderByPositionAsc(deskId).orElse(null);

            // Если колонки еще не создавали
            if (column == null) {
                DeskColumnCreateDto createDto = new DeskColumnCreateDto(
                    "Не запланировано"
                );

                column = create(deskId, createDto);
            }
        } else {
            // Иначе просто ищем нужную колонку
            column = getByIdOrNotFound(columnId);
        }

        task.setColumn(column);
    }


    // * ======================== UTILS ========================
    public void checkColumnByDeskId(DeskColumnEntity column, Long deskId) {
        if (!column.getDesk().getId().equals(deskId)) {
            throw new ResponseStatusException(
                HttpStatus.BAD_REQUEST, "Колонка не принадлежит этой доске"
            );
        }
    }

    public DeskColumnEntity getByIdOrNotFound(Long id) {
        DeskColumnEntity entity = repository.findById(id).orElse(null);

        if (entity == null) {
            throw new ResponseStatusException(
                HttpStatus.NOT_FOUND, "Колонка не найдена"
            );
        }
        return entity;
    }


}
