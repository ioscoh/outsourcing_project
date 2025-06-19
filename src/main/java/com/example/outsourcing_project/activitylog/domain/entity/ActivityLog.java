package com.example.outsourcing_project.activitylog.domain.entity;

import com.example.outsourcing_project.activitylog.dto.ActivityCreateTestDto;
import com.example.outsourcing_project.comment.domain.entity.Comment;
import com.example.outsourcing_project.member.domain.entity.Member;
import com.example.outsourcing_project.task.domain.entity.Task;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Setter
@Getter
@NoArgsConstructor
@Table(name = "activity_logs")
public class ActivityLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private LocalDateTime timestamp;

    @ManyToOne
    @JoinColumn(name = "act_member_id")
    private Member actMember;

    @Column(nullable = false, length = 50)
    private String ipAddress;

    @Column(nullable = false, length = 10)
    private String method;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String url;

    @Column(nullable = false, length = 50)
    private String activityType;

    @ManyToOne
    @JoinColumn(name = "task_id")
    private Task task;

    @ManyToOne
    @JoinColumn(name = "comment_id")
    private Comment comment;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    // 태스크 생성 테스트 생성자
    public ActivityLog(ActivityCreateTestDto activityCreateTestDto, Task task, Comment comment, Member member) {
        this.actMember = activityCreateTestDto.getAct_member_id();
        this.timestamp = LocalDateTime.now();
        this.ipAddress = activityCreateTestDto.getIp();
        this.method = activityCreateTestDto.getMethod();
        this.url = activityCreateTestDto.getUrl();
        this.activityType = activityCreateTestDto.getActivityType();
        this.task = task;
        this.comment = comment;
        this.member = member;
    }

    public ActivityLog(LocalDateTime timeStamp, Member act_user_id, String ipAddress, String method, String url, String activityType) {
        this.actMember = act_user_id;
        this.timestamp = timeStamp;
        this.ipAddress = ipAddress;
        this.method = method;
        this.url = url;
        this.activityType = activityType;
        this.task = task;
        this.comment = comment;
        this.member = member;
    }
}
