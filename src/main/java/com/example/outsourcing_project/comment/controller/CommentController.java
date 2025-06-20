package com.example.outsourcing_project.comment.controller;

import com.example.outsourcing_project.comment.dto.CommentRequestDto;
import com.example.outsourcing_project.comment.dto.CommentResponseDto;
import com.example.outsourcing_project.comment.dto.CommentUpdateRequestDto;
import com.example.outsourcing_project.comment.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;
    //댓글 작성
    @PostMapping("/tasks/{taskId}/comments")
    public ResponseEntity<CommentResponseDto> createComment(
            @PathVariable Long taskId,
            @RequestBody @Valid CommentRequestDto requestDto
    ) {
        CommentResponseDto responseDto = commentService.createComment(taskId, requestDto);
        return ResponseEntity.ok(responseDto);
    }
    //조회 (테스크별)
    @GetMapping("/tasks/{taskId}/comments")
    public ResponseEntity<List<CommentResponseDto>> getComments(@PathVariable Long taskId) {
        List<CommentResponseDto> comments = commentService.getCommentsByTaskId(taskId);
        return ResponseEntity.ok(comments);
    }
    //댓글 수정
    @PutMapping("/comments/{commentId}")
    public ResponseEntity<CommentResponseDto> updateComment(
            @PathVariable Long commentId,
            @RequestBody @Valid CommentUpdateRequestDto requestDto
    ) {
        CommentResponseDto updated = commentService.updateComment(commentId, requestDto);
        return ResponseEntity.ok(updated);
    }
    //댓글 삭제
    @DeleteMapping("/comments/{commentId}")
    public ResponseEntity<Void> deleteComment(@PathVariable Long commentId) {
        commentService.deleteComment(commentId);
        return ResponseEntity.noContent().build(); // 204 No Content
    }


}