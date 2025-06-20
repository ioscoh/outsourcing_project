package com.example.outsourcing_project.dashboard.controller;

import com.example.outsourcing_project.dashboard.dto.DashboardResponseDto;
import com.example.outsourcing_project.dashboard.service.DashboardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/dashboard")
public class DashboardController {

    private final DashboardService dashboardService;

    // jwt 주석처리
//    private Long extractUserIdFromToken(String token) {
//        String jwt = token.replace("Bearer ", "");
//        return jwtUtil.extractUserId(jwt);
//    }

    @GetMapping
    public ResponseEntity<DashboardResponseDto> getDashboard(
            // @RequestHeader("Authorization") String token
    ) {
        // 테스트용
        Long testUserId = 1L;

        DashboardResponseDto response = dashboardService.getDashboard(testUserId);
        return ResponseEntity.ok(response);
    }
}
