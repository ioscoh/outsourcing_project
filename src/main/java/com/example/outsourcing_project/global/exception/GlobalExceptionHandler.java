package com.example.outsourcing_project.global.exception;

import com.example.outsourcing_project.task.dto.api.CreateApiResDto;
import com.example.outsourcing_project.task.dto.api.DeleteApiResDto;
import com.example.outsourcing_project.task.dto.api.ReadApiResDto;
import com.example.outsourcing_project.task.dto.api.UpdateApiResDto;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Map;
import java.util.Objects;

@RestControllerAdvice
public class GlobalExceptionHandler {

    // 태스크 생성
    @ExceptionHandler(TaskCreationException.class)
    public ResponseEntity<CreateApiResDto> handleCreatefail(TaskCreationException e) {
        CreateApiResDto response = CreateApiResDto.builder()
                .status(400)
                .message("태스크 생성 실패")
                .authorId(null)
                .build();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    // 태스크 단건 조회
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ReadApiResDto<?>> handleEntityNotFound(EntityNotFoundException e) {
        ReadApiResDto<?> response = ReadApiResDto.builder()
                .message("태스크 조회가 실패했습니다.")
                .data(null)
                .build();
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    // 태스크 전체 조회
    @ExceptionHandler(InvalidQueryParameterException.class)
    public ResponseEntity<Map<String, Object>> handleInvalidQuery(InvalidQueryParameterException e) {
        return ResponseEntity.badRequest().body(Map.of(
                "status", 400,
                "message", e.getMessage()
        ));
    }

    // 태스크 수정
    @ExceptionHandler(TaskUpdateNotFoundException.class)
    public ResponseEntity<UpdateApiResDto<?>> handleNotFound(TaskUpdateNotFoundException e) {
        UpdateApiResDto<?> response = UpdateApiResDto.builder()
                .success(false)
                .message("요청이 실패했습니다.")
                .data(null)
                .build();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    // 태스크 삭제
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<DeleteApiResDto<?>> handleNotDelete(IllegalArgumentException e) {
        DeleteApiResDto<?> response = DeleteApiResDto.builder()
                .status(400)
                .message("요청이 실패했습니다.")
                .data(null)
                .build();

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }
}
