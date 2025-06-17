package com.futurenet.cotree.statistic.ecoItem.service;

import com.futurenet.cotree.statistic.ecoItem.dto.response.EcoPurchaseRatioResponse;
import com.futurenet.cotree.statistic.ecoItem.repository.EcoItemStatisticRepository;
import com.futurenet.cotree.statistic.util.RatioUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EcoItemStatisticServiceImpl implements EcoItemStatisticService{

    private final EcoItemStatisticRepository ecoItemStatisticRepository;

    @Override
    public List<EcoPurchaseRatioResponse> getEcoPurchaseRatio() {
        int totalPurchased = ecoItemStatisticRepository.getOrderItemCount();
        int ecoPurchased = ecoItemStatisticRepository.getOrderItemCount();

        double ecoRatio = RatioUtils.calculateRatio(ecoPurchased, totalPurchased);

        return Arrays.asList(
                new EcoPurchaseRatioResponse("일반 상품", 100 - ecoRatio),
                new EcoPurchaseRatioResponse("친환경 상품", ecoRatio)
        );
    }
}
