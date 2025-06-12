package com.futurenet.cotree.greenpoint.service;

import com.futurenet.cotree.greenpoint.dto.GreenPointHistoryResponse;

import java.util.List;

public interface GreenPointService {
    int getPoint(Long memberId);
    void usePoint(Long memberId, int amount);
    List<GreenPointHistoryResponse> getPointHistory(Long memberId, int page);
}
