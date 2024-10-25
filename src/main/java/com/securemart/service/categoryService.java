package com.securemart.service;

import com.securemart.payload.CategoryDTO;
import com.securemart.payload.CategoryResponse;

public interface categoryService {
    CategoryResponse getAllCategories(Integer pageNo, Integer pageSize, String sortBy, String sortOrder);

    CategoryDTO createCategory(CategoryDTO categoryDTO);

    CategoryDTO deleteCategory(Long categoryId);

    CategoryDTO updateCategory(CategoryDTO categoryDTO, Long categoryId);
}
