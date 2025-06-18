package com.example.outsourcing_project.task.dto;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Builder
public class TaskResDto {

    private Long id;
    private String title;
    private String description;
    private String priority;
    private String status;
    private Long assigneeId;
    private Long authorId;
    private LocalDate dueDate;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

}
