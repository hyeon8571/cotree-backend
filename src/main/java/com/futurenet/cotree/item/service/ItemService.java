package com.futurenet.cotree.item.service;

import com.futurenet.cotree.item.dto.response.ItemDetailResponse;
import com.futurenet.cotree.item.dto.response.ItemResponse;

import java.util.List;

public interface ItemService {
    List<ItemResponse> getItemsByCategory(Long categoryId, int page);
    ItemDetailResponse getItemDetail(Long id);
    List<ItemResponse> getEcoItems(int page);
}
