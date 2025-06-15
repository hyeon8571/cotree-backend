package com.futurenet.cotree.admin.controller;

import com.futurenet.cotree.admin.dto.response.InsightOverviewResponse;
import com.futurenet.cotree.admin.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {
    private final AdminService adminService;

    @GetMapping("/insights/overview")
    public InsightOverviewResponse getInsightsOverview() {
        return adminService.getInsightOverview();
    }
}
