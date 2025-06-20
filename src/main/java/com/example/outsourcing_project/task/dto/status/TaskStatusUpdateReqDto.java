package com.example.outsourcing_project.task.dto.status;

import com.example.outsourcing_project.task.domain.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class TaskStatusUpdateReqDto {
    private Status status;

}
