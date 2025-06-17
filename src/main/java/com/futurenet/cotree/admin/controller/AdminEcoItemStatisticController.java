package com.futurenet.cotree.admin.controller;

import com.futurenet.cotree.admin.dto.response.*;
import com.futurenet.cotree.admin.service.AdminEcoItemStatisticService;
import com.futurenet.cotree.global.dto.response.ApiResponse;
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

    @GetMapping("/purchase-count")
    public ResponseEntity<?> getEcoPurchaseCount() {
        List<EcoPurchaseCountResponse> result = adminEcoItemStatisticService.getEcoPurchaseCount();
        return ResponseEntity.ok(new ApiResponse<>("SE100", result));
    }

    @GetMapping("/popular-item")
    public ResponseEntity<?> getEcoPopularItem() {
        List<PopularEcoItemResponse> result = adminEcoItemStatisticService.getEcoPopularItem();
        return ResponseEntity.ok(new ApiResponse<>("SE101", result));
    }

    @GetMapping("/purchase-age")
    public ResponseEntity<?> getEcoPurchaseAgeCount() {
        List<EcoPurchaseAgeResponse> result =  adminEcoItemStatisticService.getEcoPurchaseAgeCount();
        return ResponseEntity.ok(new ApiResponse<>("SE102", result));
    }
    @GetMapping("/purchase-category")
    public ResponseEntity<?> getPurchaseCategory() {
        List<EcoPurchaseCategoryResponse> result = adminEcoItemStatisticService.getPurchaseCountByCategory();
        return ResponseEntity.ok(new ApiResponse<>("SE103", result));
    }

    @GetMapping("/purchase-purchase-gender")
    public ResponseEntity<?> getEcoPurchaseGenderCount() {
        List<EcoPurchaseGenderResponse> result = adminEcoItemStatisticService.getEcoPurchaseGenderCount();
        return ResponseEntity.ok(new ApiResponse<>("SE104", result));
    }
}
