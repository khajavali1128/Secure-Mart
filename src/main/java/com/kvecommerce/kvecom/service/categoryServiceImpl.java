package com.kvecommerce.kvecom.service;

import com.kvecommerce.kvecom.exceptions.APIException;
import com.kvecommerce.kvecom.exceptions.ResourceNotFoundException;
import com.kvecommerce.kvecom.model.Category;
import com.kvecommerce.kvecom.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class categoryServiceImpl implements categoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public List<Category> getAllCategories() {
        List<Category> categories = categoryRepository.findAll();
        if(categories.isEmpty())
            throw  new APIException("No Categories found!!");

        return categoryRepository.findAll(); //returns all the categories present in the database
    }

    @Override
    public void createCategory(Category category) {
        Category savedCategory = categoryRepository.findByCategoryName(category.getCategoryName());
        if(savedCategory != null) {
            throw new APIException("Category with "+ savedCategory.getCategoryName()+" already exists");
        }
        categoryRepository.save(category);
    }

    @Override
    public String deleteCategory(Long categoryId) {
        //Caterogy is being searched by findById method of Jparepository
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException("Category", "id", categoryId));
        categoryRepository.delete(category);
        return "Category deleted!";
    }

    @Override
    public Category updateCategory(Category category, Long categoryId) {
        //Caterogy is being searched by findById method of Jparepository
        Category savedCategory = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException("Category", "id", categoryId));

        category.setCategoryId(categoryId);
        savedCategory = categoryRepository.save(category);
        return savedCategory;
    }
}
