package com.futurenet.cotree.admin.service;

import com.futurenet.cotree.admin.dto.response.*;

import java.util.List;

public interface AdminEcoItemStatisticService {
    List<EcoPurchaseCountResponse> getEcoPurchaseCount();
    List<EcoPurchaseAgeResponse> getEcoPurchaseAgeCount();
    List<EcoPurchaseGenderResponse> getEcoPurchaseGenderCount();
    List<PopularEcoItemResponse> getEcoPopularItem();
    List<EcoPurchaseCategoryResponse> getPurchaseCountByCategory();
}
