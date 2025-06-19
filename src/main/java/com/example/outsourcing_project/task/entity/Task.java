package com.example.outsourcing_project.task.entity;

import com.example.outsourcing_project.member.entity.Member;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "tasks")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 50)
    private String title;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Priority priority;

    @ManyToOne
    @JoinColumn(name = "assignee_id")
    private Member assignee;

    @ManyToOne
    @JoinColumn(name = "author_id")
    private Member author;

    private LocalDateTime dueDate;

    @Enumerated(EnumType.STRING)
    private Status status;

    private LocalDateTime startedAt;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    @Column(nullable = false)
    private LocalDateTime updatedAt;

    private LocalDateTime deletedAt;

    @Column(nullable = false)
    private Boolean isDeleted = false;

    public enum Priority {
        HIGH, MEDIUM, LOW
    }

    public enum Status {
        TODO, IN_PROGRESS, DONE
    }
}
