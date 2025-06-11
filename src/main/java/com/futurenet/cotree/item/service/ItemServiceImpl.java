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

import static com.futurenet.cotree.item.policy.PaginationPolicy.PAGE_SIZE;

@Service
@RequiredArgsConstructor
public class ItemServiceImpl implements ItemService {

    private final ItemRepository itemRepository;

    @Override
    @Transactional
    public List<ItemResponse> getItemsByCategory(Long categoryId, int page) {
        int start = (page - 1) * PAGE_SIZE;

        List<Item> itemList = itemRepository.findItemsByCategory(categoryId, start, PAGE_SIZE);
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
        int start = (page - 1) * PAGE_SIZE;
        List<Item> itemList = itemRepository.getEcoItems(start, PAGE_SIZE);
        return itemList.stream()
                .map(ItemResponse::from)
                .collect(Collectors.toList());
    }
}
