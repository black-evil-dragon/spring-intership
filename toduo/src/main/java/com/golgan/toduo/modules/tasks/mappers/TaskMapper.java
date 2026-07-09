package com.golgan.toduo.modules.tasks.mappers;


import com.golgan.toduo.core.mappers.EntityMapper;
import com.golgan.toduo.modules.tasks.dto.*;
import com.golgan.toduo.modules.tasks.models.TaskEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;



@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface TaskMapper extends EntityMapper<
    TaskEntity,
    TaskCreateDto, TaskUpdateDto,
    TaskDetailDto, TaskSummaryDto, TaskListDto
    > {


    @Override
    @Mapping(target = "columnId", source = "column.id")
    TaskSummaryDto toSummaryDto(TaskEntity entity);

}