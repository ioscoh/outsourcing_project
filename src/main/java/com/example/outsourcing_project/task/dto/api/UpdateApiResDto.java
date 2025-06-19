package com.example.outsourcing_project.task.dto.api;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UpdateApiResDto<T> {

    private boolean success;
    private String message;
    private T data;
}
