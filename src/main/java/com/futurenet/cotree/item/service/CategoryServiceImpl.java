package com.futurenet.cotree.item.service;

import com.futurenet.cotree.item.repository.CategoryRepository;
import com.futurenet.cotree.item.dto.response.CategoryListReponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    @Transactional
    @Override
    public List<CategoryListReponse> getCategories() {
        return categoryRepository.getCategories();
    }
}
