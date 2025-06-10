package com.futurenet.cotree.item.service;

import com.futurenet.cotree.item.repository.response.CategoryListReponse;

import java.util.List;

public interface CategoryService {
    List<CategoryListReponse> getCategories();
}
