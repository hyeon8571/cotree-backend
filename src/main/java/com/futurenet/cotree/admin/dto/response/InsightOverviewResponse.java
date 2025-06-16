package com.futurenet.cotree.admin.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
public class InsightOverviewResponse {
    long totalRevenue;
    long newUserCount;
    long totalOrderCount;
    long ecoProductRate;
}
