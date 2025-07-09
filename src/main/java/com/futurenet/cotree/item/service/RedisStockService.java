package com.futurenet.cotree.item.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.futurenet.cotree.item.service.exception.ItemErrorCode;
import com.futurenet.cotree.item.service.exception.ItemException;
import com.futurenet.cotree.order.dto.request.OrderItemRegisterRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class RedisStockService {

    private final RedisTemplate<String, String> redisTemplate;
    private final ObjectMapper objectMapper;

    private static final String DECREASE_STOCK_LUA =
            "for i = 1, #KEYS do " +
                    "  local stock = redis.call('GET', KEYS[i]) " +
                    "  if (not stock) then return -1 end " +
                    "  if (tonumber(stock) < tonumber(ARGV[i])) then return -2 end " +
                    "end " +
                    "for i = 1, #KEYS do " +
                    "  redis.call('DECRBY', KEYS[i], ARGV[i]) " +
                    "end " +
                    "return 1";

    public void decreaseStock(List<OrderItemRegisterRequest> items, Long memberId) {
        List<String> keys = items.stream()
                .map(item -> "stock:" + item.getItemId())
                .collect(Collectors.toList());

        List<String> quantities = items.stream()
                .map(item -> String.valueOf(item.getQuantity()))
                .toList();

        DefaultRedisScript<Long> redisScript = new DefaultRedisScript<>();
        redisScript.setScriptText(DECREASE_STOCK_LUA);
        redisScript.setResultType(Long.class);

        String[] args = quantities.toArray(new String[0]);

        Long result = redisTemplate.execute(redisScript, keys, args);

        if (result == null) {
            throw new ItemException(ItemErrorCode.ITEM_NOT_FOUND);
        }

        if (result == -1L) {
            throw new ItemException(ItemErrorCode.ITEM_NOT_FOUND);
        }

        if (result == -2L) {
            throw new ItemException(ItemErrorCode.ITEM_QUANTITY_LACK);
        }

        for (OrderItemRegisterRequest item : items) {
            try {
                Map<String, Object> logEntry = new HashMap<>();
                logEntry.put("itemId", item.getItemId());
                logEntry.put("memberId", memberId);
                logEntry.put("quantity", item.getQuantity());
                logEntry.put("timestamp", LocalDateTime.now().toString());

                String json = objectMapper.writeValueAsString(logEntry);
                redisTemplate.opsForList().leftPush("stock:queue", json);
            } catch (Exception e) {
                log.error("Redis 큐 적재 실패", e);
            }
        }
    }
}
