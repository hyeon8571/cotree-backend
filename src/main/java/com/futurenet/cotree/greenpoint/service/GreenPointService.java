package com.futurenet.cotree.greenpoint.service;

import com.futurenet.cotree.greenpoint.dto.GreenPointHistoryResponse;
import com.futurenet.cotree.greenpoint.dto.GreenPointSummaryResponse;

import java.util.List;


import com.futurenet.cotree.greenpoint.dto.GreenPointSaveRequest;

public interface GreenPointService {
    int getPoint(Long memberId);
    void usePoint(Long memberId, int amount);
    List<GreenPointHistoryResponse> getPointHistory(Long memberId, int page);
    GreenPointSummaryResponse getGreenPointSummary(Long memberId);
    void savePoint(GreenPointSaveRequest greenPointSaveRequest);
}
