package com.example.outsourcing_project.task.controller;

import com.example.outsourcing_project.task.dto.TaskReqDto;
import com.example.outsourcing_project.task.dto.TaskResDto;
import com.example.outsourcing_project.task.service.TaskService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/tasks")
public class TaskController {

    private final TaskService taskService;

    // 태스크 생성
    @PostMapping
    public ResponseEntity<TaskResDto> createTask(@RequestBody @Valid TaskReqDto request) {
        Long authorId = 1L;
        TaskResDto response = taskService.createTask(request, authorId);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }



}
