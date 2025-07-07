package com.futurenet.cotree.item.service;

import com.futurenet.cotree.item.domain.EventItem;
import com.futurenet.cotree.item.domain.Item;
import com.futurenet.cotree.item.repository.EventItemRepository;
import com.futurenet.cotree.item.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class EventScheduler {

    private final EventItemRepository eventItemRepository;
    private final ItemRepository itemRepository;
    private final RedisTemplate<String, Object> redisTemplate;

    @Scheduled(cron = "0 22 18 * * *")
    @Transactional
    public void registerEventItems() {

        LocalDateTime now = LocalDateTime.now();
        LocalDateTime eventStart = now.toLocalDate().atTime(20, 0);
        LocalDateTime eventEnd = now.toLocalDate().atTime(21, 0);


        List<Item> items = itemRepository.getEventItems();

        List<EventItem> eventItems = items.stream()
                .map(item -> EventItem.builder()
                        .itemId(item.getId())
                        .eventDiscount(item.getPrice() / 2)
                        .eventStartTime(eventStart)
                        .eventEndTime(eventEnd)
                        .build())
                .toList();

        int result = eventItemRepository.saveEventItems(eventItems);

        if (result == 0) {
            log.info("행사 상품 등록 실패");
        } else {
            items.forEach(item -> {
                String key = "stock:" + item.getId();
                int quantity = item.getQuantity();
                redisTemplate.opsForValue().set(key, String.valueOf(quantity));
            });
        }
    }
}
