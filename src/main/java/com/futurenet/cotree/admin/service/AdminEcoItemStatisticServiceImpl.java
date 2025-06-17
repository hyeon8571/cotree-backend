package com.futurenet.cotree.admin.service;

import com.futurenet.cotree.admin.dto.response.EcoPurchaseCategoryResponse;
import com.futurenet.cotree.admin.dto.response.EcoPurchaseRatioResponse;
import com.futurenet.cotree.admin.dto.response.PopularEcoItemResponse;
import com.futurenet.cotree.admin.repository.AdminEcoItemStatisticRepository;
import com.futurenet.cotree.admin.util.RatioUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminEcoItemStatisticServiceImpl implements AdminEcoItemStatisticService {

    private final AdminEcoItemStatisticRepository adminEcoItemStatisticRepository;

    @Override
    public List<EcoPurchaseRatioResponse> getEcoPurchaseRatio() {
        int totalPurchased = adminEcoItemStatisticRepository.getOrderItemCount();
        int ecoPurchased = adminEcoItemStatisticRepository.getEcoOrderItemCount();

        if(totalPurchased == 0 && ecoPurchased == 0){
            return Arrays.asList(
                    new EcoPurchaseRatioResponse("일반 상품", 0 ),
                    new EcoPurchaseRatioResponse("친환경 상품", 0)
            );
        }

        double ecoRatio = RatioUtil.calculateRatio(ecoPurchased, totalPurchased);
        return Arrays.asList(
                new EcoPurchaseRatioResponse("일반 상품", 100 - ecoRatio),
                new EcoPurchaseRatioResponse("친환경 상품", ecoRatio)
        );
    }

    @Override
    @Transactional
    public List<PopularEcoItemResponse> getEcoPopularItem() {
        return adminEcoItemStatisticRepository.getPopularEcoItems();
    }

    @Override
    public List<EcoPurchaseCategoryResponse> getPurchaseCountByCategory() {
        return adminEcoItemStatisticRepository.getPurchaseByCategory();
    }
}
