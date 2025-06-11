package com.futurenet.cotree.item.controller;

import com.futurenet.cotree.global.dto.response.ApiResponse;
import com.futurenet.cotree.item.dto.response.CategoryListReponse;
import com.futurenet.cotree.item.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryService categoryService;

    @GetMapping("/categories")
    public ResponseEntity<?> getCategories(){
        List<CategoryListReponse> result = categoryService.getCategories();
        return ResponseEntity.ok(new ApiResponse<>("CA100", result));
    }
}
