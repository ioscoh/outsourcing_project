package com.example.outsourcing_project.global.exception;

import com.example.outsourcing_project.task.dto.api.CreateApiResDto;
import com.example.outsourcing_project.task.dto.api.ReadApiResDto;
import com.example.outsourcing_project.task.dto.api.UpdateApiResDto;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    // 태스크 생성
    @ExceptionHandler(IllegalAccessException.class)
    public ResponseEntity<CreateApiResDto> handleCreatefail(IllegalArgumentException e) {
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

    // 태스크 수정
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<UpdateApiResDto<?>> handleNotFound(EntityNotFoundException e) {
        UpdateApiResDto<?> response = UpdateApiResDto.builder()
                .success(false)
                .message("요청이 실패했습니다.")
                .data(null)
                .build();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }
}
