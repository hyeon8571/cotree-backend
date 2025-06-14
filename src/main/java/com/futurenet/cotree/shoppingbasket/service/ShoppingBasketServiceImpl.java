package com.futurenet.cotree.shoppingbasket.service;

import com.futurenet.cotree.item.dto.response.ItemDetailResponse;
import com.futurenet.cotree.item.service.ItemService;
import com.futurenet.cotree.shoppingbasket.dto.response.ShoppingBasketItemsResponse;
import com.futurenet.cotree.shoppingbasket.repository.ShoppingBasketRepository;
import com.futurenet.cotree.shoppingbasket.service.exception.ShoppingBasketErrorCode;
import com.futurenet.cotree.shoppingbasket.service.exception.ShoppingBasketException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ShoppingBasketServiceImpl implements ShoppingBasketService {

    private final ShoppingBasketRepository shoppingBasketRepository;
    private final ItemService itemService;

    @Override
    @Transactional
    public void registerShoppingBasket(Long memberId) {
        shoppingBasketRepository.saveShoppingBasket(memberId);
    }

    @Override
    public List<ShoppingBasketItemsResponse> getAllShoppingBasketItemsByMemberId(Long memberId) {
        return shoppingBasketRepository.getAllShoppingBasketItemsByMemberId(memberId);
    }

    @Override
    @Transactional
    public void saveBasketItem(Long memberId, Long itemId, Integer quantity) {
        ItemDetailResponse itemDetail = itemService.getItemDetail(itemId);
        if (itemDetail == null) {
            throw new ShoppingBasketException(ShoppingBasketErrorCode.ITEM_NOT_FOUND);
        }

        int stock = itemDetail.getQuantity();
        if (quantity > stock) {
            throw new ShoppingBasketException(ShoppingBasketErrorCode.QUANTITY_EXCEEDS_STOCK);
        }

        Long basketItemId = shoppingBasketRepository.getBasketItemId(memberId, itemId);

        if (basketItemId == null) {
            int inserted = shoppingBasketRepository.saveBasketItem(memberId, itemId, quantity);
            if (inserted != 1) {
                throw new ShoppingBasketException(ShoppingBasketErrorCode.INSERT_FAILED);
            }
            return;
        }

        int currentQuantity = shoppingBasketRepository.getBasketItemQuantity(memberId, itemId);
        if (currentQuantity + quantity > stock) {
            throw new ShoppingBasketException(ShoppingBasketErrorCode.QUANTITY_EXCEEDS_STOCK);
        }

        int updated = shoppingBasketRepository.updateBasketItemQuantity(basketItemId, quantity);
        if (updated != 1) {
            throw new ShoppingBasketException(ShoppingBasketErrorCode.UPDATE_FAILED);
        }
    }

    @Override
    @Transactional
    public void deleteBasketItem(Long memberId, Long basketItemId) {
        if (basketItemId == null || basketItemId <= 0) {
            throw new ShoppingBasketException(ShoppingBasketErrorCode.INVALID_BASKET_ITEM_ID);
        }

        int deleted = shoppingBasketRepository.deleteBasketItem(memberId, basketItemId);

        if (deleted != 1) {
            throw new ShoppingBasketException(ShoppingBasketErrorCode.BASKET_ITEM_NOT_FOUND);
        }
    }

    @Override
    public int countBasketItems(Long memberId) {
        return shoppingBasketRepository.countBasketItems(memberId);
    }
}
