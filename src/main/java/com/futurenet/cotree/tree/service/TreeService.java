package com.futurenet.cotree.tree.service;

import com.futurenet.cotree.tree.dto.request.GiveWaterRequest;
import com.futurenet.cotree.tree.dto.response.GiveWaterResponse;
import com.futurenet.cotree.tree.dto.response.MyTreeResponse;

public interface TreeService {
    void registerTree(Long memberId);
    MyTreeResponse getMyTree(Long memberId);
    GiveWaterResponse giveWater(Long memberId, GiveWaterRequest giveWaterRequest);
    GiveWaterResponse giveAllWater(Long memberId, GiveWaterRequest giveWaterRequest);
}
