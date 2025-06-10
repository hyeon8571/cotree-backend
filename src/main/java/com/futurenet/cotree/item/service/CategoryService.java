package com.futurenet.cotree.item.service;

import com.futurenet.cotree.item.repository.response.CategoryListReponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CategoryService {
    List<CategoryListReponse>  getCategories();
}
