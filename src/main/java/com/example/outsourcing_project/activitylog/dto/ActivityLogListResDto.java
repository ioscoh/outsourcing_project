package com.example.outsourcing_project.activitylog.dto;

import lombok.Getter;

import java.util.List;
@Getter
public class ActivityLogListResDto {
    private List<ActivityLogDto> logList;

    public ActivityLogListResDto(List<ActivityLogDto> activityLogDtoList) {
        this.logList = activityLogDtoList;
    }
}