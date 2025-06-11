package com.futurenet.cotree.shoppingbasket.service;

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
        if(itemId == null) throw new ShoppingBasketException(ShoppingBasketErrorCode.INVALID_ITEM_ID);
        if(quantity==null || quantity <= 0) throw new ShoppingBasketException(ShoppingBasketErrorCode.INVALID_QUANTITY);

        // ...
        // TODO: (아이템 서비스 연동) itemId가 유효하고, 실제로 존재하는 상품인지 확인하는 로직 추가 필요.
        //       - 아이템 Repository를 사용해야 함.
        //       - 상품이 없을 경우 ShoppingBasketErrorCode.ITEM_NOT_FOUND 예외 발생.
        // --------------------------------------------------------

        Long basketItemId = shoppingBasketRepository.getBasketItemId(memberId, itemId);

        if (basketItemId != null) {
            int updated = shoppingBasketRepository.updateBasketItemQuantity(basketItemId, quantity);
            if (updated != 1) {
                throw new ShoppingBasketException(ShoppingBasketErrorCode.UPDATE_FAILED);
            }
            return;

        }

        int inserted = shoppingBasketRepository.saveBasketItem(memberId, itemId, quantity);
        if (inserted != 1) {
            throw new ShoppingBasketException(ShoppingBasketErrorCode.INSERT_FAILED);
        }
    }
}
