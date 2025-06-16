package com.example.outsourcing_project.activitylog.domain.entity;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.time.LocalDateTime;

public class ActivityLogs {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private long actUserId;
    private LocalDateTime timeStamp;
    private String ip;
    private String httpMethod;
    private String request_url;
    private String activityType;
    private long taskId;
    private long commentId;
    private long userId;

    //JPA 에서 사용됩니다
    public ActivityLogs() {}

}