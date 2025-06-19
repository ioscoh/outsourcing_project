package com.example.outsourcing_project.activitylog.dto;

import com.example.outsourcing_project.member.domain.entity.Member;
import com.example.outsourcing_project.task.domain.entity.Task;
import lombok.Getter;

import java.time.LocalDateTime;
@Getter
public class ActivityLogDto {
    private LocalDateTime timestamp;
    private Member memberId;
    private String ip;
    private String method;
    private String url;
    private String activityType;
    private Task taskId;

    public ActivityLogDto(
            LocalDateTime timestamp,
            Member memberId,
            String ip,
            String method,
            String url,
            String activityType,
            Task taskId
    ) {
        this.timestamp = timestamp;
        this.memberId = memberId;
        this.ip = ip;
        this.method = method;
        this.url = url;
        this.activityType = activityType;
        this.taskId = taskId;
    }
}
