package com.futurenet.cotree.item.service.batch;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.futurenet.cotree.item.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Slf4j
@Component
@RequiredArgsConstructor
public class StockBatchProcessor {

    private final RedisTemplate<String, String> redisTemplate;
    private final ItemRepository itemRepository;
    private final ObjectMapper objectMapper;

    private static final String QUEUE_KEY = "stock:queue";
    private static final String FAIL_QUEUE_KEY = "stock:fail";

    @Scheduled(cron = "0 */3 * * * *")
    @Transactional
    public void syncStockBatch() {
        int batchSize = 100;
        List<String> logs = redisTemplate.opsForList().range(QUEUE_KEY, -batchSize, -1);
        if (logs == null || logs.isEmpty()) {
            return;
        }

        for (String logEntryJson : logs) {
            try {
                Map<String, Object> entry = objectMapper.readValue(logEntryJson, new TypeReference<>() {});
                Long itemId = Long.parseLong(entry.get("itemId").toString());
                int quantity = Integer.parseInt(entry.get("quantity").toString());

                int updated = itemRepository.decreaseEventItemStock(itemId, quantity);

                if (updated > 0) {
                    redisTemplate.opsForList().remove(QUEUE_KEY, 1, logEntryJson);
                } else {
                    log.warn("Redis -> RDB 수량 반영 오류 발생 itemId={}, qty={}", itemId, quantity);
                    redisTemplate.opsForList().leftPush(FAIL_QUEUE_KEY, logEntryJson);
                }

            } catch (Exception e) {
                log.error("처리 중 오류: {}", e.getMessage());
                redisTemplate.opsForList().leftPush(FAIL_QUEUE_KEY, logEntryJson);
            }
        }
    }
}

