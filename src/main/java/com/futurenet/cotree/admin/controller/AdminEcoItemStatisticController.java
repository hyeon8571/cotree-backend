package com.futurenet.cotree.admin.controller;

import com.futurenet.cotree.admin.dto.response.EcoPurchaseCategoryResponse;
import com.futurenet.cotree.admin.dto.response.PopularEcoItemResponse;
import com.futurenet.cotree.global.dto.response.ApiResponse;
import com.futurenet.cotree.admin.dto.response.EcoPurchaseRatioResponse;
import com.futurenet.cotree.admin.service.AdminEcoItemStatisticService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin/statistics/eco")
public class AdminEcoItemStatisticController {

    private final AdminEcoItemStatisticService adminEcoItemStatisticService;

    @GetMapping("/purchase-ratio")
    public ResponseEntity<?> getEcoPurchaseRatio() {
        List<EcoPurchaseRatioResponse> result = adminEcoItemStatisticService.getEcoPurchaseRatio();
        return ResponseEntity.ok(new ApiResponse<>("SE100", result));
    }

    @GetMapping("/popular-item")
    public ResponseEntity<?> getEcoPopularItem() {
        List<PopularEcoItemResponse> result = adminEcoItemStatisticService.getEcoPopularItem();
        return ResponseEntity.ok(new ApiResponse<>("SE101", result));
    }

    @GetMapping("/purchase-category")
    public ResponseEntity<?> getPurchaseCategory() {
        List<EcoPurchaseCategoryResponse> result = adminEcoItemStatisticService.getPurchaseCountByCategory();
        return ResponseEntity.ok(new ApiResponse<>("SE103", result));
    }
}
