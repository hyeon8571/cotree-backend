package com.futurenet.cotree.order.controller;

import com.futurenet.cotree.auth.security.dto.UserPrincipal;
import com.futurenet.cotree.global.dto.response.ApiResponse;
import com.futurenet.cotree.order.dto.request.OrderRequest;
import com.futurenet.cotree.order.service.OrderFacadeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderFacadeService orderFacadeService;

    @PostMapping
    public ResponseEntity<?> createOrder(@Valid @RequestBody OrderRequest orderRequest,
                                         @AuthenticationPrincipal UserPrincipal userPrincipal) {
        String orderNumber = orderFacadeService.registerOrder(orderRequest, userPrincipal.getId());
        return ResponseEntity.ok(new ApiResponse<>("OR100", orderNumber));
    }
}
