package com.futurenet.cotree.shoppingbasket.repository;

import com.futurenet.cotree.shoppingbasket.dto.response.ShoppingBasketItemsResponse;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ShoppingBasketRepository {
    void saveShoppingBasket(Long memberId);
    List<ShoppingBasketItemsResponse> getAllShoppingBasketItemsByMemberId(Long memberId);
    Long getBasketItemId(@Param("memberId") Long memberId, @Param("itemId") Long itemId);
    int updateBasketItemQuantity(@Param("basketItemId") Long basketItemId, @Param("quantity") Integer quantity);
    int saveBasketItem(@Param("memberId") Long memberId, @Param("itemId") Long itemId, @Param("quantity") Integer quantity);
    int deleteBasketItem(@Param("memberId") Long memberId, @Param("basketItemId") Long basketItemId);
}
