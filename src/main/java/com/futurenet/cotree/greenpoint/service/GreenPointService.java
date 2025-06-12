package com.futurenet.cotree.greenpoint.service;

import com.futurenet.cotree.greenpoint.dto.GreenPointSaveRequest;

public interface GreenPointService {
    int getPoint(Long memberId);
    void usePoint(Long memberId, int amount);
    void savePoint(GreenPointSaveRequest greenPointSaveRequest);
}
