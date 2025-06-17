package com.futurenet.cotree.statistic.ecoItem.service;

import com.futurenet.cotree.statistic.ecoItem.dto.response.EcoPurchaseRatioResponse;

import java.util.List;

public interface EcoItemStatisticService {
    List<EcoPurchaseRatioResponse> getEcoPurchaseRatio();
}
