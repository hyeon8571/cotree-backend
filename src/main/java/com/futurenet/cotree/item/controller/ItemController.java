package com.futurenet.cotree.item.controller;

import com.futurenet.cotree.global.dto.response.ApiResponse;
import com.futurenet.cotree.item.dto.response.ItemDetailResponse;
import com.futurenet.cotree.item.dto.response.ItemResponse;
import com.futurenet.cotree.item.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/items")
public class ItemController {

    private final ItemService itemService;

    @GetMapping
    public ResponseEntity<?> getItemsByCategory(@RequestParam(name = "categoryId", required = false, defaultValue = "0") Long categoryId, @RequestParam int page) {
        List<ItemResponse> result = itemService.getItemsByCategory(categoryId, page);
        return ResponseEntity.ok(new ApiResponse<>("IT100", result));
    }

    @GetMapping("/{itemId}")
    public ResponseEntity<?> getItemDetail (@PathVariable Long itemId) {
        ItemDetailResponse result = itemService.getItemDetail(itemId);
        return ResponseEntity.ok(new ApiResponse<>("IT101", result));
    }

    @GetMapping("/eco")
    public ResponseEntity<?> getEcoItems(@RequestParam int page){
        List<ItemResponse> result = itemService.getEcoItems(page);
        return ResponseEntity.ok(new ApiResponse<>("IT102", result));
    }
}
