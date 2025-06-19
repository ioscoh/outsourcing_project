package com.example.outsourcing_project.task.controller;

import com.example.outsourcing_project.task.domain.enums.Status;
import com.example.outsourcing_project.task.dto.*;
import com.example.outsourcing_project.task.dto.api.CreateApiResDto;
import com.example.outsourcing_project.task.dto.api.ReadApiResDto;
import com.example.outsourcing_project.task.dto.api.UpdateApiResDto;
import com.example.outsourcing_project.task.dto.page.PagedTaskResDto;
import com.example.outsourcing_project.task.dto.page.TaskSummaryResDto;
import com.example.outsourcing_project.task.dto.status.TaskStatusUpdateReqDto;
import com.example.outsourcing_project.task.dto.updete.TaskUpdateReqDto;
import com.example.outsourcing_project.task.service.TaskService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/tasks")
public class TaskController {

    private final TaskService taskService;

    // 태스크 생성
    @PostMapping
    public ResponseEntity<CreateApiResDto> createTask(@RequestBody @Valid TaskReqDto request) {
        Long authorId = 1L;
        taskService.createTask(request, authorId);

        CreateApiResDto response = CreateApiResDto.builder()
                .status(200)
                .message("테스크 생성되었습니다.")
                .authorId(authorId)
                .build();

        return ResponseEntity.ok(response);
    }

    // 단건 조회
    @GetMapping("/{id}")
    public ResponseEntity<ReadApiResDto<TaskResDto>> getTask(@PathVariable Long id) {
        TaskResDto task = taskService.getTask(id);

        ReadApiResDto<TaskResDto> response = ReadApiResDto.<TaskResDto>builder()
                .message("태스크 조회가 완료되었습니다.")
                .data(task)
                .build();

        return ResponseEntity.ok(response);
    }

    // 전체 조회
    @GetMapping
    public ResponseEntity<PagedTaskResDto> getTasks(
            @RequestParam(required = false) String search,
            Pageable pageable) {

        Page<TaskResDto> taskPage = taskService.searchTasks(search, pageable);

        List<TaskSummaryResDto> taskList = taskPage.getContent().stream()
                .map(task -> TaskSummaryResDto.builder()
                        .id(task.getId())
                        .title(task.getTitle())
                        .status(Status.valueOf(task.getStatus()))
                        .assigneeId(task.getAssigneeId())
                        .createdAt(task.getCreatedAt())
                        .build())
                .toList();

        PagedTaskResDto response = new PagedTaskResDto(
                taskList,
                taskPage.getNumber(),
                taskPage.getSize(),
                taskPage.getTotalPages(),
                taskPage.getTotalElements()
        );

        return ResponseEntity.ok(response);
    }

    // 태스크 수정
    @PutMapping("/{id}")
    public ResponseEntity<UpdateApiResDto<TaskResDto>> updateTask(
            @PathVariable Long id,
            @RequestBody @Valid TaskUpdateReqDto request
    ) {
        TaskResDto updatedTask = taskService.updateTask(id, request);

        UpdateApiResDto<TaskResDto> response = UpdateApiResDto.<TaskResDto>builder()
                .success(true)
                .message("요청이 성공했습니다.")
                .data(updatedTask)
                .build();

        return ResponseEntity.ok(response);

    }

    // 상태 변경
    @PatchMapping("/{id}/status")
    public ResponseEntity<TaskResDto> updateTaskStatus(
            @PathVariable Long id,
            @RequestBody @Valid TaskStatusUpdateReqDto request) {

        TaskResDto response = taskService.updateTaskStatus(id, request.getStatus());
        return ResponseEntity.ok(response);
    }

    // 태스크 삭제
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable Long id) {
        taskService.deleteTask(id);
        return ResponseEntity.noContent().build();
    }




}
