package com.example.outsourcing_project.task.dto.updete;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;

@AllArgsConstructor
@Getter
public class TaskUpdateReqDto {
    private String title;
    private String description;
    private String priority;
    private LocalDate dueDate;
    private Long assigneeId;

}
