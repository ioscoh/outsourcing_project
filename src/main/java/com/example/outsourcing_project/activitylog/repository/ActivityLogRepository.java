package com.example.outsourcing_project.activitylog.repository;

import com.example.outsourcing_project.activitylog.domain.entity.ActivityLog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ActivityLogRepository extends JpaRepository<ActivityLog, Long> {

}
