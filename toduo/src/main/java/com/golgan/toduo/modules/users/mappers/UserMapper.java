package com.golgan.toduo.modules.users.mappers;


import com.golgan.toduo.core.mappers.EntityMapper;
import com.golgan.toduo.modules.users.dto.*;
import com.golgan.toduo.modules.users.models.UserEntity;

import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;



@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface UserMapper extends EntityMapper<
    UserEntity,
    UserCreateDto, UserUpdateDto,
    UserDetailDto, UserSummaryDto, UserListDto
    > {}