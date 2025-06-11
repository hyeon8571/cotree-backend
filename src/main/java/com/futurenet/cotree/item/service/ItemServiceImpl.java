package com.futurenet.cotree.item.service;

import com.futurenet.cotree.item.dto.ItemDto;
import com.futurenet.cotree.item.dto.response.ItemResponse;
import com.futurenet.cotree.item.repository.ItemRepository;
import com.futurenet.cotree.item.service.exception.ItemErrorCode;
import com.futurenet.cotree.item.service.exception.ItemException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ItemServiceImpl implements ItemService {
    private final ItemRepository itemRepository;

    @Override
    @Transactional
    public List<ItemResponse> getItemsByCategory(Long categoryId) {
        List<ItemDto> itemList = itemRepository.findItemsByCategory(categoryId);
        return itemList.stream()
                .map(ItemResponse::from)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void decreaseStock(Long itemId, int quantity) {
        int result = itemRepository.decreaseStock(itemId, quantity);

        if (result == 0) {
            throw new ItemException(ItemErrorCode.ITEM_QUANTITY_LACK);
        }
    }
}
