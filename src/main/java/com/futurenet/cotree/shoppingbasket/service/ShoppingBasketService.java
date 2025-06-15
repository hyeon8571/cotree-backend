package com.futurenet.cotree.shoppingbasket.service;

import com.futurenet.cotree.auth.security.dto.UserPrincipal;
import com.futurenet.cotree.shoppingbasket.dto.response.ShoppingBasketItemsResponse;

import java.util.List;

public interface ShoppingBasketService {
    void registerShoppingBasket(Long memberId);
    List<ShoppingBasketItemsResponse> getAllShoppingBasketItemsByMemberId(Long memberId);
    void saveBasketItem(UserPrincipal userPrincipal, Long itemId, Integer quantity);
    void deleteBasketItem(Long memberId, Long basketItemId);
    int countBasketItems(Long memberId);
    void deleteBasketItemsByMemberIdAndItemIds(Long memberId, List<Long> itemIds);
}
