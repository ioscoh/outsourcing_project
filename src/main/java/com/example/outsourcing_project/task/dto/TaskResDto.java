package com.example.outsourcing_project.task.dto;

import com.example.outsourcing_project.task.domain.entity.Task;
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
    private LocalDate startedAt;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public static TaskResDto from(Task task) {
        return TaskResDto.builder()
                .id(task.getId())
                .title(task.getTitle())
                .description(task.getDescription())
                .priority(task.getPriority().name())
                .status(task.getStatus().name())
                .authorId(task.getAuthor().getId())
                .assigneeId(task.getAssignee() != null ? task.getAssignee().getId() : null)
                .dueDate(task.getDueDate())
                .startedAt(task.getStartedAt())
                .createdAt(task.getCreatedAt())
                .updatedAt(task.getUpdatedAt())
                .build();
    }
}
