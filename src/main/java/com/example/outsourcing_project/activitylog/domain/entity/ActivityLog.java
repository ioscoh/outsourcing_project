package com.example.outsourcing_project.activitylog.domain.entity;

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
    private LocalDateTime timestemp;

    @ManyToOne
    @JoinColumn(name = "act_member_id")
    private Member actMember;       // 멤버로 변경

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






}
