package com.futurenet.cotree.shoppingbasket.service;

import com.futurenet.cotree.shoppingbasket.domain.ShoppingBasket;
import com.futurenet.cotree.shoppingbasket.dto.response.ShoppingBasketItemsResponse;
import com.futurenet.cotree.shoppingbasket.repository.ShoppingBasketRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ShoppingBasketServiceImpl implements ShoppingBasketService {

    private final ShoppingBasketRepository shoppingBasketRepository;

    @Override
    @Transactional
    public void registerShoppingBasket(Long memberId) {
        shoppingBasketRepository.saveShoppingBasket(memberId);
    }

    @Override
    public List<ShoppingBasketItemsResponse> getAllShoppingBasketItemsByMemberId(Long memberId) {
        return shoppingBasketRepository.getAllShoppingBasketItemsByMemberId(memberId);
    }
}
