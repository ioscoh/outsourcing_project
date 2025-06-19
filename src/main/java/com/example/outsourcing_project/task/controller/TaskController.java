package com.example.outsourcing_project.task.controller;

import com.example.outsourcing_project.task.dto.TaskReqDto;
import com.example.outsourcing_project.task.dto.TaskResDto;
import com.example.outsourcing_project.task.service.TaskService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/tasks")
public class TaskController {

    private final TaskService taskService;

    // 태스크 생성
    @PostMapping
    public ResponseEntity<TaskResDto> createTask(@RequestBody @Valid TaskReqDto request, HttpServletRequest requestIp) {
        String ip = requestIp.getRemoteAddr();
        Long authorId = 1L;
        TaskResDto response = taskService.createTask(request, authorId, ip);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }



}
