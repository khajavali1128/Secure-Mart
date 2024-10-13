package com.kvecommerce.kvecom.service;

import com.kvecommerce.kvecom.model.Category;

import java.util.List;

public interface categoryService {
    List<Category> getAllCategories();
    void createCategory(Category category);


    String deleteCategory(Long categoryId);


    Category updateCategory(Category category, Long categoryId);
}
