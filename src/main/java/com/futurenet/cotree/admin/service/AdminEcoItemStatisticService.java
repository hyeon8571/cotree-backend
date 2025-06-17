package com.futurenet.cotree.admin.service;

import com.futurenet.cotree.admin.dto.response.EcoPurchaseCategoryResponse;
import com.futurenet.cotree.admin.dto.response.EcoPurchaseRatioResponse;
import com.futurenet.cotree.admin.dto.response.PopularEcoItemResponse;

import java.util.List;

public interface AdminEcoItemStatisticService {
    List<EcoPurchaseRatioResponse> getEcoPurchaseRatio();
    List<PopularEcoItemResponse> getEcoPopularItem();
    List<EcoPurchaseCategoryResponse> getPurchaseCountByCategory();
}
