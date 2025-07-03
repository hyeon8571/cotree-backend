package com.futurenet.cotree.item.controller;

import com.futurenet.cotree.global.dto.response.ApiResponse;
import com.futurenet.cotree.item.dto.response.ItemResponse;
import com.futurenet.cotree.item.service.EventItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/items/event")
public class EventItemController {

    private final EventItemService eventItemService;

    @GetMapping
    public ResponseEntity<?> getEventItems() {
        List<ItemResponse> result = eventItemService.getEventItems();
        return ResponseEntity.ok(new ApiResponse<>("IT105", result));
    }
}
