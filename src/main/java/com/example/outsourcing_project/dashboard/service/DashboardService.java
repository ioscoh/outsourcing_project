package com.example.outsourcing_project.dashboard.service;

import com.example.outsourcing_project.dashboard.dto.DashboardResponseDto;
import com.example.outsourcing_project.dashboard.dto.DashboardTaskDto;
import com.example.outsourcing_project.dashboard.repository.DashboardRepository;
import com.example.outsourcing_project.task.domain.enums.Status;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DashboardService {

    private final DashboardRepository dashboardRepository;

    public DashboardResponseDto getDashboard(Long userId) {
        // 전체 통계 (TaskStats)
        long total = dashboardRepository.countByIsDeletedFalse();
        long todo = dashboardRepository.countByStatusAndIsDeletedFalse(Status.TODO);
        long inProgress = dashboardRepository.countByStatusAndIsDeletedFalse(Status.IN_PROGRESS);
        long done = dashboardRepository.countByStatusAndIsDeletedFalse(Status.DONE);

        double completionRate = total > 0 ? Math.round((double) done / total * 10000) / 100.0 : 0.0;

        long overdue = dashboardRepository.countOverdueUncompletedTasks(
                Arrays.asList(Status.TODO, Status.IN_PROGRESS),
                LocalDate.now()
        );

        DashboardResponseDto.TaskStats taskStats = new DashboardResponseDto.TaskStats(
                total, todo, inProgress, done, completionRate, overdue
        );

        // 오늘 내 태스크 (UserTodayTasks)
        List<DashboardTaskDto> todayTodos = dashboardRepository.findTodayTasksByUserAndStatus(
                userId, Status.TODO, LocalDate.now()
        );

        List<DashboardTaskDto> todayInProgress = dashboardRepository.findTodayTasksByUserAndStatus(
                userId, Status.IN_PROGRESS, LocalDate.now()
        );

        DashboardResponseDto.UserTodayTasks userTodayTasks = new DashboardResponseDto.UserTodayTasks(
                todayTodos, todayInProgress
        );

        // 응답 조합
        return new DashboardResponseDto(taskStats, userTodayTasks);
    }
}
