package com.futurenet.cotree.admin.service;

import com.futurenet.cotree.admin.dto.response.EcoPurchaseAgeResponse;
import com.futurenet.cotree.admin.dto.response.EcoPurchaseCountResponse;
import com.futurenet.cotree.admin.dto.response.EcoPurchaseGenderResponse;

import java.util.List;

public interface AdminEcoItemStatisticService {
    List<EcoPurchaseCountResponse> getEcoPurchaseCount();
    List<EcoPurchaseAgeResponse> getEcoPurchaseAgeCount();
    List<EcoPurchaseGenderResponse> getEcoPurchaseGenderCount();
}
