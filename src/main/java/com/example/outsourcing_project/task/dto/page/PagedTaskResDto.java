package com.example.outsourcing_project.task.dto.page;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class PagedTaskResDto {
    private List<TaskSummaryResDto> tasks;
    private int page;
    private int size;
    private int totalPages;
    private long totalElements;
}
