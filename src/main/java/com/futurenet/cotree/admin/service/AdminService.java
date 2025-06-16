package com.futurenet.cotree.admin.service;

import com.futurenet.cotree.admin.dto.request.AdminLoginRequest;
import com.futurenet.cotree.admin.dto.response.AdminLoginResponse;
import com.futurenet.cotree.admin.dto.response.InsightOverviewResponse;
import com.futurenet.cotree.admin.dto.response.PointStat;

import java.util.List;

public interface AdminService {
    InsightOverviewResponse getInsightOverview();
    List<PointStat> getPointStatsByRange(String range);
    AdminLoginResponse login(AdminLoginRequest adminLoginRequest);
}
