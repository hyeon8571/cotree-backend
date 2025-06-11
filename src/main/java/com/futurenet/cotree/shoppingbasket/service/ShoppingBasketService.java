package com.futurenet.cotree.shoppingbasket.service;

import com.futurenet.cotree.shoppingbasket.dto.response.ShoppingBasketItemsResponse;

import java.util.List;

public interface ShoppingBasketService {
    void registerShoppingBasket(Long memberId);
    List<ShoppingBasketItemsResponse> getAllShoppingBasketItemsByMemberId(Long memberId);
    void saveBasketItem(Long memberId, Long itemId, Integer quantity);
}
