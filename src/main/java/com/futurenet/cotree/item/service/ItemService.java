package com.futurenet.cotree.item.service;

import com.futurenet.cotree.item.dto.response.ItemResponse;

import java.util.List;

public interface ItemService {
    List<ItemResponse> getItemsByCategory(Long categoryId);
    void decreaseStock(Long itemId, int quantity);
}
