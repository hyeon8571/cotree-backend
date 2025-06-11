package com.futurenet.cotree.greenpoint.service;

public interface GreenPointService {
    int getPoint(Long memberId);
    void usePoint(Long memberId, int amount);
}
