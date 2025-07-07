package com.futurenet.cotree.order.controller;

import com.futurenet.cotree.auth.security.dto.UserPrincipal;
import com.futurenet.cotree.global.dto.response.ApiResponse;
import com.futurenet.cotree.order.async.cunsumer.OrderRequestConsumer;
import com.futurenet.cotree.order.async.dto.request.OrderRequestWithMember;
import com.futurenet.cotree.order.dto.request.OrderRequest;
import com.futurenet.cotree.order.dto.response.OrderDetailResponse;
import com.futurenet.cotree.order.dto.response.OrderResponse;
import com.futurenet.cotree.order.service.OrderFacadeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.BlockingQueue;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderFacadeService orderFacadeService;
    private final OrderRequestConsumer orderRequestConsumer;

    @PostMapping
    public ResponseEntity<?> createOrder(@Valid @RequestBody OrderRequest orderRequest,
                                         @AuthenticationPrincipal UserPrincipal userPrincipal) {
        String orderNumber = orderFacadeService.registerOrder(orderRequest, userPrincipal.getId());
        return ResponseEntity.ok(new ApiResponse<>("OR100", orderNumber));
    }

    @GetMapping
    public ResponseEntity<?> getOrders(@AuthenticationPrincipal UserPrincipal userPrincipal,
                                       @RequestParam(name = "status", required = false) String status,
                                       @RequestParam(required = false, defaultValue = "1") int page) {
        List<OrderResponse> result = orderFacadeService.getOrdersByMember(userPrincipal.getId(), status, page);
        return ResponseEntity.ok(new ApiResponse<>("OR101", result));
    }

    @GetMapping("/{orderNumber}")
    public ResponseEntity<?> getOrderDetail(@AuthenticationPrincipal UserPrincipal userPrincipal,
                                            @PathVariable String orderNumber) {
        OrderDetailResponse result = orderFacadeService.getOrderDetail(orderNumber, userPrincipal.getId());
        return ResponseEntity.ok(new ApiResponse<>("OR102", result));
    }

    @PostMapping("/async")
    public ResponseEntity<?> createOrders(@Valid @RequestBody OrderRequest orderRequest,
                                         @AuthenticationPrincipal UserPrincipal userPrincipal) {
        orderRequestConsumer.addRequest(new OrderRequestWithMember(orderRequest, userPrincipal.getId()));
        return ResponseEntity.ok(new ApiResponse<>("OR101", "주문 접수"));
    }
}
