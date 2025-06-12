package com.futurenet.cotree.greenpoint.service;

import com.futurenet.cotree.greenpoint.dto.GreenPointHistoryResponse;


public interface GreenPointService {
    int getPoint(Long memberId);
    void usePoint(Long memberId, int amount);
    GreenPointHistoryResponse getPointHistory(Long memberId, int page);
}
