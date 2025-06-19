package com.example.outsourcing_project.comment.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import jakarta.validation.constraints.NotBlank;

@Getter
@Setter
@NoArgsConstructor
public class CommentRequestDto {
    @NotBlank(message = "댓글 내용은 비어 있을 수 없습니다.")
    private String content;

    public CommentRequestDto(String content) {
        this.content = content;
    }
}
