package com.example.outsourcing_project.task.service;

import com.example.outsourcing_project.member.repository.MemberRepository;
import com.example.outsourcing_project.member.domain.entity.Member;
import com.example.outsourcing_project.task.domain.entity.Task;
import com.example.outsourcing_project.task.dto.TaskReqDto;
import com.example.outsourcing_project.task.dto.TaskResDto;
import com.example.outsourcing_project.task.repository.TaskRepository;
import jakarta.transaction.Transactional;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
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
                .assigneeId(assignee)
                .authorId(author)
                .dueDate(dto.getDueDate())
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
                .assigneeId(task.getAssigneeId().getId())
                .authorId(task.getAuthorId().getId())
                .dueDate(task.getDueDate())
                .createdAt(task.getCreatedAt())
                .updatedAt(task.getUpdatedAt())
                .build();
    }

}
