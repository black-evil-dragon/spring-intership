package com.golgan.toduo.modules.desks.mappers;


import com.golgan.toduo.core.mappers.EntityMapper;
import com.golgan.toduo.modules.desks.dto.*;
import com.golgan.toduo.modules.desks.models.DeskColumnEntity;

import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;



@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface DeskColumnMapper extends EntityMapper<
    DeskColumnEntity,
    DeskColumnCreateDto, DeskColumnUpdateDto,
    DeskColumnDetailDto, DeskColumnSummaryDto, DeskColumnListDto
    > {

}