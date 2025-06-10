package com.futurenet.cotree.item.controller;

import com.futurenet.cotree.item.repository.response.CategoryListReponse;
import com.futurenet.cotree.item.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryService categoryService;

    @GetMapping("/categories")
    public List<CategoryListReponse> getCategories(){
        return categoryService.getCategories();
    }

}
