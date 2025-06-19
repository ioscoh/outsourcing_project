package com.example.outsourcing_project.task.service;

import com.example.outsourcing_project.member.repository.MemberRepository;
import com.example.outsourcing_project.member.domain.entity.Member;
import com.example.outsourcing_project.task.domain.entity.Task;
import com.example.outsourcing_project.task.domain.enums.Priority;
import com.example.outsourcing_project.task.domain.enums.Status;
import com.example.outsourcing_project.task.dto.TaskReqDto;
import com.example.outsourcing_project.task.dto.TaskResDto;
import com.example.outsourcing_project.task.dto.updete.TaskUpdateReqDto;
import com.example.outsourcing_project.task.exception.TaskNotFoundException;
import com.example.outsourcing_project.task.repository.TaskRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@Getter
@Builder
@RequiredArgsConstructor
@Transactional
public class TaskService {

    private final TaskRepository taskRepository;
    private final MemberRepository memberRepository;

    // 태스크 생성
    public TaskResDto createTask(TaskReqDto dto, Long authorId) {
        Member assignee = memberRepository.findById(dto.getAssigneeId())
                .orElseThrow(() -> new IllegalArgumentException("해당 담당자가 존재하지 않습니다."));
        Member author = memberRepository.findById(authorId)
                .orElseThrow(() -> new IllegalArgumentException("해당 생성자 정보가 존재하지 않습니다."));

        Task task = Task.builder()
                .title(dto.getTitle())
                .description(dto.getDescription())
                .priority(dto.getPriority())
                .status(dto.getStatus())
                .assignee(assignee)
                .author(author)
                .dueDate(dto.getDueDate())
                .startedAt(dto.getStartedAt())
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .isDeleted(false)
                .build();

        taskRepository.save(task);

        return toDto(task);
    }

    private TaskResDto toDto(Task task) {
        return TaskResDto.builder()
                .id(task.getId())
                .title(task.getTitle())
                .description(task.getDescription())
                .priority(task.getPriority().name())
                .status(task.getStatus().name())
                .assigneeId(task.getAssignee().getId())
                .authorId(task.getAuthor().getId())
                .dueDate(task.getDueDate())
                .startedAt(task.getStartedAt())
                .createdAt(task.getCreatedAt())
                .updatedAt(task.getUpdatedAt())
                .build();
    }

    // 단건 조회
    public TaskResDto getTask(Long taskId) {
        Task task = findTask(taskId);
        return toDto(task);
    }

    private Task findTask(Long taskId) {
        return taskRepository.findById(taskId)
                .filter(task -> !task.getIsDeleted())
                .orElseThrow(() -> new EntityNotFoundException("해당 태스크를 찾을 수 없습니다."));
    }

    // 전체 조회
    public Page<TaskResDto> searchTasks(String search, Pageable pageable) {
        Page<Task> tasks;
        if (search == null || search.trim().isEmpty()) {
            // 검색어 없을 때는 전체 조회
            tasks = taskRepository.findAllByIsDeletedFalse(pageable);
        } else {
            // 검색어 있을 때는 제목이나 설명에 서치 포함하고 삭제 되지 않은 것만 조회
            tasks = taskRepository.findByTitleContainingOrDescriptionContainingAndIsDeletedFalse(
                    search, search, pageable
            );
        }
        return tasks.map(this::toDto);
    }

    // 태스크 수정
    public TaskResDto updateTask(Long id, TaskUpdateReqDto dto) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new TaskNotFoundException(id));

        Priority priority = Priority.valueOf(dto.getPriority());

        Member assigneeId = memberRepository.findById(dto.getAssigneeId())
                        .orElseThrow(() -> new TaskNotFoundException(dto.getAssigneeId()));

        task.update(
                dto.getTitle(),
                dto.getDescription(),
                priority,
                dto.getDueDate(),
                assigneeId
        );

        return TaskResDto.from(task);

    }

    // 상태 변경
    public TaskResDto updateTaskStatus(Long id, String status) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new TaskNotFoundException(id));

        task.updateStatus(Status.valueOf(status));
        return TaskResDto.from(task);
    }

    // 태스크 삭제
    @Transactional
    public void deleteTask(Long id) {
        Task task = findTask(id);
        task.setIsDeleted(true);
    }

}
