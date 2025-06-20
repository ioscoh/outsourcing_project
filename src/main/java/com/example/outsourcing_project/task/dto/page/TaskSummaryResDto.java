package com.example.outsourcing_project.task.dto.page;

import com.example.outsourcing_project.task.domain.entity.Task;
import com.example.outsourcing_project.task.domain.enums.Status;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class TaskSummaryResDto {
    private Long id;
    private String title;
    private Status status;
    private Long assigneeId;
    private LocalDateTime createdAt;

    public static TaskSummaryResDto from(Task task) {
        return TaskSummaryResDto.builder()
                .id(task.getId())
                .title(task.getTitle())
                .status(Status.valueOf(task.getStatus().name()))
                .assigneeId(task.getAssignee().getId())
                .createdAt(task.getCreatedAt())
                .build();

    }

}
