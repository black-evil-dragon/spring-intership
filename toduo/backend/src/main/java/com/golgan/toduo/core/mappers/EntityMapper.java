package com.golgan.toduo.core.mappers;

import org.mapstruct.MappingTarget;


import java.util.List;


// TODO DELETE COMMENT: Вообще в задумках разбить его еще на мелкие части, но боюсь сделать излишне
public interface EntityMapper<
    Entity,
    CreateDto, UpdateDto,
    DetailDto, SummaryDto, ListDto
    > {

    // Update entity from dto
    void update(UpdateDto dto, @MappingTarget Entity entity);


    // From dto to entity
    Entity toEntity(CreateDto dto);


    // From entity to dto
    DetailDto toDetailDto(Entity entity);

    SummaryDto toSummaryDto(Entity entity);

    ListDto toListDto(Entity entity);
}