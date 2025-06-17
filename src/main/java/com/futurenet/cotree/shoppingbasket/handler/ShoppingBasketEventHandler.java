package com.futurenet.cotree.shoppingbasket.handler;

import com.futurenet.cotree.order.dto.request.OrderItemRegisterRequest;
import com.futurenet.cotree.shoppingbasket.dto.request.ShoppingBasketDeleteRequestEvent;
import com.futurenet.cotree.shoppingbasket.service.ShoppingBasketService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionalEventListener;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ShoppingBasketEventHandler {

    private final ShoppingBasketService shoppingBasketService;

    @Async
    @TransactionalEventListener
    public void handleDeleteEvent(ShoppingBasketDeleteRequestEvent deleteRequestEvent) {
        List<Long> itemIds = deleteRequestEvent.getOrderItems().stream()
                .map(OrderItemRegisterRequest::getItemId)
                .toList();

        shoppingBasketService.deleteBasketItemsByMemberIdAndItemIds(deleteRequestEvent.getMemberId(), itemIds);
    }
}
