package com.golgan.toduo.modules.desks.dto;

import com.golgan.toduo.modules.tasks.dto.TaskSummaryDto;

import java.util.List;

public record DeskColumnDetailDto(
    Long id,
    String name,
    Integer position,

    List<TaskSummaryDto> tasks
) {
}