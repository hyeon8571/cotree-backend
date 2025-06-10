package com.futurenet.cotree.shoppingbasket.repository;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ShoppingBasketRepository {
    void saveShoppingBasket(Long memberId);
}
