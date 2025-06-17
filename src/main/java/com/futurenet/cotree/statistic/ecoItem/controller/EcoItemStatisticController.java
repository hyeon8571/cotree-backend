package com.futurenet.cotree.statistic.ecoItem.controller;

import com.futurenet.cotree.global.dto.response.ApiResponse;
import com.futurenet.cotree.statistic.ecoItem.dto.response.EcoPurchaseRatioResponse;
import com.futurenet.cotree.statistic.ecoItem.service.EcoItemStatisticService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/statistics/eco")
public class EcoItemStatisticController {

    private final EcoItemStatisticService ecoItemStatisticService;

    @GetMapping("/purchase-ratio")
    public ResponseEntity<?> getEcoPurchaseRatio() {
        List<EcoPurchaseRatioResponse> result = ecoItemStatisticService.getEcoPurchaseRatio();
        return ResponseEntity.ok(new ApiResponse<>("SE100", result));
    }
}
