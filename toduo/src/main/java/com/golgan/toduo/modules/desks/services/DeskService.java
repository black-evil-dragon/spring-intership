package com.golgan.toduo.modules.desks.services;


import com.golgan.toduo.core.services.CRUDService;
import com.golgan.toduo.modules.desks.dto.DeskCreateDto;
import com.golgan.toduo.modules.desks.dto.DeskUpdateDto;
import com.golgan.toduo.modules.desks.mappers.DeskMapper;
import com.golgan.toduo.modules.desks.models.DeskEntity;
import com.golgan.toduo.modules.desks.repositories.DeskRepository;
import com.golgan.toduo.modules.users.models.UserEntity;
import com.golgan.toduo.modules.users.services.UserService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;


@Service
public class DeskService extends CRUDService<DeskEntity, DeskRepository, Long> {

    private final DeskMapper mapper;

    private final UserService userService;



    protected DeskService(
        DeskRepository repository,
        DeskMapper mapper,
        UserService userService
    ) {
        super(repository);

        this.mapper = mapper;
        this.userService = userService;
    }


    @Transactional
    public DeskEntity create(@Valid @RequestBody DeskCreateDto createDto) {
        DeskEntity desk = mapper.toEntity(createDto);

        setOwnerByUserId(createDto.ownerId(), desk);

        return repository.save(desk);
    }


    @Transactional
    public DeskEntity update(Long id, @Valid @RequestBody DeskUpdateDto updateDto) {
        DeskEntity desk = getByIdOrNotFound(id);

        mapper.update(updateDto, desk);

        if (updateDto.ownerId() != null) {
            setOwnerByUserId(updateDto.ownerId(), desk);
        }


        return repository.save(desk);
    }

    // * ======================== UTILS ========================
    public void setOwnerByUserId(Long userId, DeskEntity desk) {
        UserEntity user = userService.getUserOrNotFound(userId);

        desk.setOwner(user);
    }
}
