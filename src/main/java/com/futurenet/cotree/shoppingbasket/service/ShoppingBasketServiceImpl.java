package com.futurenet.cotree.shoppingbasket.service;

import com.futurenet.cotree.shoppingbasket.repository.ShoppingBasketRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ShoppingBasketServiceImpl implements ShoppingBasketService {

    private final ShoppingBasketRepository shoppingBasketRepository;

    @Override
    @Transactional
    public void registerShoppingBasket(Long memberId) {
        shoppingBasketRepository.saveShoppingBasket(memberId);
    }
}
