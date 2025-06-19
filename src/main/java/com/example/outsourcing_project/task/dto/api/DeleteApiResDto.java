package com.example.outsourcing_project.task.dto.api;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class DeleteApiResDto<T> {
    private int status;
    private String message;
    private T data;

}
