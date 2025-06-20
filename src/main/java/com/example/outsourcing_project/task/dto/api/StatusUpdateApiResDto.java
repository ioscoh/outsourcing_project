package com.example.outsourcing_project.task.dto.api;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
@AllArgsConstructor
public class StatusUpdateApiResDto {
    private Long id;
    private String status;
    private LocalDateTime updatedAt;
}
