package com.example.outsourcing_project.activitylog.service;

import com.example.outsourcing_project.activitylog.domain.entity.ActivityLog;
import com.example.outsourcing_project.activitylog.dto.ActivityCreateTestDto;
import com.example.outsourcing_project.activitylog.dto.ActivityLogDto;
import com.example.outsourcing_project.activitylog.dto.ActivityLogListResDto;
import com.example.outsourcing_project.activitylog.repository.ActivityLogRepository;
import com.example.outsourcing_project.member.domain.entity.Member;
import com.example.outsourcing_project.member.repository.MemberRepository;
import com.example.outsourcing_project.task.domain.entity.Task;
import com.example.outsourcing_project.task.repository.TaskRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class ActivityLogService {

    private final MemberRepository memberRepository;
    private final TaskRepository taskRepository;
    private final ActivityLogRepository activityLogRepository;

    public ActivityLogService(MemberRepository memberRepository, TaskRepository taskRepository, ActivityLogRepository activityLogRepository) {
        this.memberRepository = memberRepository;
        this.taskRepository = taskRepository;
        this.activityLogRepository = activityLogRepository;
    }


    public void createtest(ActivityCreateTestDto activityCreateTestDto) {

//        Long userId = activityCreateTestDto.getUserId();
//        Member member = memberRepository.findById(userId)
//                .orElseThrow(() -> new RuntimeException());
//        Long taskId = activityCreateTestDto.getTaskId();
//        Task task = taskRepository.findById(taskId)
//                .orElseThrow(() -> new RuntimeException());
        ActivityLog activityLog = new ActivityLog(activityCreateTestDto, null, null, null);
        activityLogRepository.save(activityLog);
    }

    public ActivityLogListResDto getAllActivityLogs() {
        // 1 엔티티 목록 조회하기
        List<ActivityLog> activityLogList = activityLogRepository.findAll();

        // 2 목적 ActivityLogResDto 객체를 리스트로 만들기
        List<ActivityLogDto> activityTaskLogDtoList = new ArrayList<>();

        // 3 ActivityResDtoList 로 깍아야한다
        for (ActivityLog activityLog : activityLogList) {
            LocalDateTime timestamp = activityLog.getTimestamp();
            Member memberId = activityLog.getMember();
            String ipAddress = activityLog.getIpAddress();
            String method = activityLog.getMethod();
            String url = activityLog.getUrl();
            String activityType = activityLog.getActivityType();
            Task taskId = activityLog.getTask();
            ActivityLogDto activityLogDto = new ActivityLogDto(timestamp, memberId, ipAddress, method, url, activityType, taskId);
            activityTaskLogDtoList.add(activityLogDto);
        }
        // 4 ActivityLogResDtoList 최종 Dto 만들기
        ActivityLogListResDto activityLogListResDto = new ActivityLogListResDto(activityTaskLogDtoList);
        return activityLogListResDto;
    }

}
