package com.golgan.toduo.modules.desks.mappers;


import com.golgan.toduo.core.mappers.EntityMapper;
import com.golgan.toduo.modules.desks.models.DeskEntity;
import com.golgan.toduo.modules.desks.dto.*;

import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;


@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface DeskMapper extends EntityMapper<
    DeskEntity,
    DeskCreateDto, DeskUpdateDto,
    DeskDetailDto, DeskSummaryDto, DeskListDto
    > {

}