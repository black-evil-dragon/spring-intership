package com.golgan.toduo.modules.users.mappers;


import com.golgan.toduo.modules.users.dto.*;
import com.golgan.toduo.modules.users.models.UserEntity;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;


@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface UserMapper {
    // Update
    void update(UserUpdateDto dto, @MappingTarget UserEntity entity);


    // From dto
    UserEntity toEntity(UserCreateDto dto);



    // From entity
    UserDetailDto toDetailDto(UserEntity user);

    UserSummaryDto toSummaryDto(UserEntity user);

    UserListDto toListDto(UserEntity user);
    List<UserListDto> toListDtoCollection(List<UserEntity> users);
}