package com.futurenet.cotree.greenpoint.service;

import com.futurenet.cotree.greenpoint.dto.GreenPointHistoryResponse;
import com.futurenet.cotree.greenpoint.dto.GreenPointSummaryResponse;

import java.util.List;


public interface GreenPointService {
    int getPoint(Long memberId);
    void usePoint(Long memberId, int amount);
    List<GreenPointHistoryResponse> getPointHistory(Long memberId, int page);
    GreenPointSummaryResponse getGreenPointSummary(Long memberId);
}
