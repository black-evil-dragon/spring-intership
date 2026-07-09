package com.golgan.toduo.modules.desks.services;


import com.golgan.toduo.modules.desks.dto.DeskCreateDto;
import com.golgan.toduo.modules.desks.dto.DeskUpdateDto;
import com.golgan.toduo.modules.desks.exceptions.DeskNotFoundException;
import com.golgan.toduo.modules.desks.mappers.DeskMapper;
import com.golgan.toduo.modules.desks.models.DeskEntity;
import com.golgan.toduo.modules.desks.repositories.DeskRepository;
import com.golgan.toduo.modules.users.models.UserEntity;
import com.golgan.toduo.modules.users.services.UserService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;


@Service
@RequiredArgsConstructor
public class DeskService {

    private final DeskMapper mapper;
    private final DeskRepository repository;

    private final UserService userService;


    // * ======================== READ ========================
    public Page<DeskEntity> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    public DeskEntity findById(Long id) {
        return getDeskOrNotFound(id);
    }


    // * ======================== CREATE ========================
    @Transactional
    public DeskEntity create(@Valid @RequestBody DeskCreateDto createDto) {
        DeskEntity desk = mapper.toEntity(createDto);

        setOwnerByUserId(createDto.ownerId(), desk);

        return repository.save(desk);
    }


    // * ======================== UPDATE ========================
    @Transactional
    public DeskEntity update(Long id, @Valid @RequestBody DeskUpdateDto updateDto) {
        DeskEntity desk = getDeskOrNotFound(id);

        mapper.update(updateDto, desk);

        if (updateDto.ownerId() != null) {
            setOwnerByUserId(updateDto.ownerId(), desk);
        }


        return repository.save(desk);
    }


    // * ======================== DELETE ========================
    public void deleteById(Long id) {
    }


    // * ======================== UTILS ========================
    public void setOwnerByUserId(Long userId, DeskEntity desk) {
        UserEntity user = userService.getUserOrNotFound(userId);

        desk.setOwner(user);
    }


    public DeskEntity getDeskOrNotFound(DeskEntity entity) {
        if (entity == null) {
            throw new DeskNotFoundException();
        }
        return entity;
    }

    public DeskEntity getDeskOrNotFound(Long id) {
        DeskEntity entity = repository.findById(id).orElse(null);

        return getDeskOrNotFound(entity);
    }



}
