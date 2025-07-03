package com.futurenet.cotree.item.service;

import com.futurenet.cotree.item.domain.Item;
import com.futurenet.cotree.item.dto.response.ItemResponse;
import com.futurenet.cotree.item.repository.EventItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EventItemServiceImpl implements EventItemService {

    private final EventItemRepository eventItemRepository;

    @Override
    @Transactional
    public List<ItemResponse> getEventItems() {
        List<Item> itemList = eventItemRepository.getEventItems();
        return itemList.stream()
                .map(ItemResponse::from)
                .collect(Collectors.toList());
    }
}
