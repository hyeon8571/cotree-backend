package com.futurenet.cotree.shoppingbasket.repository;

import com.futurenet.cotree.shoppingbasket.dto.response.ShoppingBasketItemsResponse;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ShoppingBasketRepository {
    void saveShoppingBasket(Long memberId);
    List<ShoppingBasketItemsResponse> getAllShoppingBasketItemsByMemberId(Long memberId);
}
