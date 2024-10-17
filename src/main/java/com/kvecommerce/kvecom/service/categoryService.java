package com.kvecommerce.kvecom.service;

import com.kvecommerce.kvecom.payload.CategoryDTO;
import com.kvecommerce.kvecom.payload.CategoryResponse;

public interface categoryService {
    CategoryResponse getAllCategories(Integer pageNo, Integer pageSize, String sortBy, String sortOrder);

    CategoryDTO createCategory(CategoryDTO categoryDTO);

    CategoryDTO deleteCategory(Long categoryId);

    CategoryDTO updateCategory(CategoryDTO categoryDTO, Long categoryId);
}
