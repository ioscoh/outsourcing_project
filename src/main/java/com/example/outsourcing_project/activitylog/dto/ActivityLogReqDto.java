package com.example.outsourcing_project.activitylog.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
@Getter
@Setter
public class ActivityLogReqDto {
    private LocalDateTime timestamp;
    private long userId;
    private String ip;
    private String method;
    private String url;
    private String activityType;
    private long taskId;
}
