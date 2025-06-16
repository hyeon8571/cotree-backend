package com.futurenet.cotree.admin.controller;

import com.futurenet.cotree.admin.dto.response.InsightOverviewResponse;
import com.futurenet.cotree.admin.dto.response.PointStat;
import com.futurenet.cotree.admin.service.AdminService;
import com.futurenet.cotree.global.dto.response.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {
    private final AdminService adminService;

    @GetMapping("/insights/overview")
    public ResponseEntity<?> getInsightsOverview() {
        InsightOverviewResponse insightOverviewResponse = adminService.getInsightOverview();
        return ResponseEntity.ok(new ApiResponse<>("AD100", insightOverviewResponse));

    }

    @GetMapping("/insights/points")
    public ResponseEntity<?> getPointStatsByRange(@RequestParam String range) {
        List<PointStat> pointStats= adminService.getPointStatsByRange(range);
        return ResponseEntity.ok(new ApiResponse<>("AD100", pointStats));


    }
}

