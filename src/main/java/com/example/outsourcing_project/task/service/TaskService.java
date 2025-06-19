package com.example.outsourcing_project.task.service;

import com.example.outsourcing_project.activitylog.domain.entity.ActivityLog;
import com.example.outsourcing_project.activitylog.repository.ActivityLogRepository;
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
    private final ActivityLogRepository activityLogRepository;

    public TaskResDto createTask(TaskReqDto dto, Long authorId, String ip) {
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

        Task savedTask = taskRepository.save(task);
        // 1 엔티티 준비하기
        //요청시간
        LocalDateTime timeStamp = LocalDateTime.now();
        //활동한 사용자 Id
        Member act_user_id = savedTask.getAuthorId();
        //사용 메서드
        String method = "POST";
        //url 주소
        String url = "/tasks";
        //ActivityType
        String activityType = "TASK_CREATED";
        //객체화 후 로그 리포짓에 저장
        ActivityLog activityLog = new ActivityLog(timeStamp, act_user_id, ip, method, url, activityType);
        activityLogRepository.save(activityLog);

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
