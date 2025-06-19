package com.example.outsourcing_project.task.dto.api;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class CreateApiResDto {

    private int status;
    private String message;
    private Long authorId;

    public static CreateApiResDto success(String message, Long authorId) {
        return CreateApiResDto.builder()
                .status(200)
                .message(message)
                .authorId(authorId)
                .build();
    }

}
