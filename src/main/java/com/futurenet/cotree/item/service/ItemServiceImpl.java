package com.futurenet.cotree.item.service;

import com.futurenet.cotree.item.domain.Item;
import com.futurenet.cotree.item.dto.response.ItemDetailResponse;
import com.futurenet.cotree.item.dto.response.ItemResponse;
import com.futurenet.cotree.item.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ItemServiceImpl implements ItemService {
    private final ItemRepository itemRepository;
    final int size = 20; //한 페이지에서 보여줄 상품 개수


    @Override
    @Transactional
    public List<ItemResponse> getItemsByCategory(Long categoryId, int page) {
        int start = (page - 1) * size;

        List<Item> itemList = itemRepository.findItemsByCategory(categoryId, start, size);
        return itemList.stream()
                .map(ItemResponse::from)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public ItemDetailResponse getItemDetail(Long id) {
        return ItemDetailResponse.from(itemRepository.findItemDetailById(id));
    }

    @Override
    @Transactional
    public List<ItemResponse> getEcoItems(int page) {
        int start = (page - 1) * size;
        List<Item> itemList = itemRepository.getEcoItems(start, size);
        return itemList.stream()
                .map(ItemResponse::from)
                .collect(Collectors.toList());
    }
}
