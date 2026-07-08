package com.golgan.toduo.modules.tasks.mappers;


import com.golgan.toduo.modules.tasks.dto.*;
import com.golgan.toduo.modules.tasks.models.TaskEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;


@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface TaskMapper {
    // Update
    void update(TaskUpdateDto dto, @MappingTarget TaskEntity entity);


    // From dto
    TaskEntity toEntity(TaskCreateDto dto);



    // From entity
    TaskDetailDto toDetailDto(TaskEntity Task);

    TaskSummaryDto toSummaryDto(TaskEntity Task);

    TaskListDto toListDto(TaskEntity Task);
    List<TaskListDto> toListDtoCollection(List<TaskEntity> Tasks);
}