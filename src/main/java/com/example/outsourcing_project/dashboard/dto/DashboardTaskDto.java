package com.example.outsourcing_project.dashboard.dto;

import com.example.outsourcing_project.task.domain.enums.Priority;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DashboardTaskDto {
    private Long id;
    private String title;
    private Priority priority;
    private LocalDate dueDate;
}
