package com.futurenet.cotree.payment.controller;

import com.futurenet.cotree.auth.security.dto.UserPrincipal;
import com.futurenet.cotree.global.dto.response.ApiResponse;
import com.futurenet.cotree.payment.dto.request.PaymentConfirmRequest;
import com.futurenet.cotree.payment.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/payments")
@RequiredArgsConstructor
public class PaymentController {

    private final PaymentService paymentService;

    @PostMapping
    public ResponseEntity<?> pay(@AuthenticationPrincipal UserPrincipal userPrincipal,
                                 @RequestBody PaymentConfirmRequest paymentConfirmRequest) {

        paymentService.payConfirm(paymentConfirmRequest, userPrincipal.getId());
        return ResponseEntity.ok().body(new ApiResponse<>("PA100", null));
    }
}
