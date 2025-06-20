package com.example.outsourcing_project.task.dto.deleted;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class DeletedTaskResDto {
    private LocalDateTime deletedAt;
}
