package com.example.outsourcing_project.activitylog.controller;

import com.example.outsourcing_project.activitylog.service.ActivityLogService;
import jakarta.persistence.Id;
import org.springframework.web.bind.annotation.*;

import java.nio.file.Path;

@RestController
@RequestMapping("/ActivityLogs")
public class ActivityLogController {

    private final ActivityLogService activityLogService;

    public ActivityLogController(ActivityLogService activityLogService) {
        this.activityLogService = activityLogService;
    }

    @GetMapping("/logs")
    public String getActivityLog(@RequestParam long id) {

        return "process";
    }

}
