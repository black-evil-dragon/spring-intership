package com.golgan.toduo.modules.tasks.mappers;


import com.golgan.toduo.core.mappers.EntityMapper;
import com.golgan.toduo.modules.tasks.dto.*;
import com.golgan.toduo.modules.tasks.models.TaskEntity;

import org.mapstruct.Mapper;

import org.mapstruct.NullValuePropertyMappingStrategy;



@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface TaskMapper extends EntityMapper<
    TaskEntity,
    TaskCreateDto, TaskUpdateDto,
    TaskDetailDto, TaskSummaryDto, TaskListDto
    > {

}