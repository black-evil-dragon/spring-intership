package com.golgan.toduo.modules.desks.dto;

import com.golgan.toduo.modules.tasks.dto.TaskSummaryDto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.List;

public record DeskColumnDetailDto(
    Long id,
    String name,
    Integer position,

    List<TaskSummaryDto> tasks
) {
}