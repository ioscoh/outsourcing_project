package com.example.outsourcing_project.task.dto.api;

import com.example.outsourcing_project.task.dto.page.TaskSummaryResDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
@AllArgsConstructor
public class AllReadApiResDto {
    private List<TaskSummaryResDto> task;
    private int page;
    private int size;
    private int totalPages;
    private long totalElements;
}
