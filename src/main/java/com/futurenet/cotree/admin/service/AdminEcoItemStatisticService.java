package com.futurenet.cotree.admin.service;

import com.futurenet.cotree.admin.dto.response.EcoPurchaseRatioResponse;

import java.util.List;

public interface AdminEcoItemStatisticService {
    List<EcoPurchaseRatioResponse> getEcoPurchaseRatio();
}
