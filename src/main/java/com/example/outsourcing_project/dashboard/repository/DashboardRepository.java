package com.example.outsourcing_project.dashboard.repository;

import com.example.outsourcing_project.dashboard.dto.DashboardTaskDto;
import com.example.outsourcing_project.task.domain.enums.Status;
import com.example.outsourcing_project.task.domain.entity.Task;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface DashboardRepository extends Repository<Task, Long> {

    long countByIsDeletedFalse();

    long countByStatusAndIsDeletedFalse(Status status);

    @Query("SELECT COUNT(t) FROM Task t " +
            "WHERE t.status IN (:statuses) " +
            "AND t.dueDate < :today " +
            "AND t.isDeleted = false")
    long countOverdueUncompletedTasks(
            @Param("statuses") List<Status> statuses,
            @Param("today") LocalDate today
    );

    @Query("SELECT new com.example.outsourcing_project.dashboard.dto.DashboardTaskDto(" +
            "t.id, t.title, t.priority, t.dueDate) " +
            "FROM Task t " +
            "WHERE t.id = :userId " +
            "AND t.status = :status " +
            "AND t.dueDate = :today " +
            "AND t.isDeleted = false " +
            "ORDER BY t.priority DESC")
    List<DashboardTaskDto> findTodayTasksByUserAndStatus(
            @Param("userId") Long userId,
            @Param("status") Status status,
            @Param("today") LocalDate today
    );
}
