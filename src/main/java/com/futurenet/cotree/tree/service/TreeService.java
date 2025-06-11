package com.futurenet.cotree.tree.service;

import com.futurenet.cotree.tree.dto.request.GiveWaterRequest;
import com.futurenet.cotree.tree.dto.response.GiveWaterResponse;

public interface TreeService {
    void registerTree(Long memberId);
    int getMyTree(Long memberId);
    GiveWaterResponse giveWater(Long memberId, GiveWaterRequest giveWaterRequest);
}
