package com.example.outsourcing_project.activitylog.controller;

import com.example.outsourcing_project.activitylog.dto.ActivityCreateTestDto;
import com.example.outsourcing_project.activitylog.dto.ActivityLogListResDto;
import com.example.outsourcing_project.activitylog.service.ActivityLogService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/ActivityLogs")
public class ActivityLogController {

    private final ActivityLogService activityLogService;

    public ActivityLogController(ActivityLogService activityLogService) {
        this.activityLogService = activityLogService;
    }

    @PostMapping("/logtestcreate")
    public void logcreatetest(@RequestBody ActivityCreateTestDto activityCreateTestDto) {
        activityLogService.createtest(activityCreateTestDto);
    }

    @GetMapping("/Logs")
    public ActivityLogListResDto getActivityLogs(@RequestParam Long memberId) {
        ActivityLogListResDto activityLogListResDto = activityLogService.getAllActivityLogs();
        return activityLogListResDto;
    }
}
