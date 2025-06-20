package com.example.outsourcing_project.dashboard.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DashboardResponseDto {

    private TaskStats taskStats;
    private UserTodayTasks userTodayTasks;

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class TaskStats {
        private long totalTaskCount;
        private long todoCount;
        private long inProgressCount;
        private long doneCount;
        private double completionRate;
        private long overdueCount;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class UserTodayTasks {
        private List<DashboardTaskDto> todoTasks;
        private List<DashboardTaskDto> inProgressTasks;
    }
}
