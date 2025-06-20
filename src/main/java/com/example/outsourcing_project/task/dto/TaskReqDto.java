package com.example.outsourcing_project.task.dto;

import com.example.outsourcing_project.task.domain.enums.Priority;
import com.example.outsourcing_project.task.domain.enums.Status;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class TaskReqDto {

    @NotBlank(message = "제목을 입력해주세요.")
    private String title;

    private String description;

    @NotNull(message = "우선순위를 입력해주세요.")
    private Priority priority;

    @NotNull(message = "담당자 ID를 입력해주세요.")
    private Long assigneeId;

    private LocalDate dueDate;

    private LocalDate startedAt;

    @NotNull(message = "상태를 입력해주세요.")
    private Status status;



}
