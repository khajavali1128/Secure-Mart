package com.kvecommerce.kvecom.service;

import com.kvecommerce.kvecom.model.Category;
import com.kvecommerce.kvecom.repositories.CategoryRepository;
//mport io.micrometer.common.KeyValues;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class categoryServiceImpl implements categoryService {

    private Long nexId  = 1L;

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public List<Category> getAllCategories() {
        return categoryRepository.findAll(); //returns all the categories present in the database
    }

    @Override
    public void createCategory(Category category) {
        //save is method to insert the item into the repo(here i=categoryRepositry)
        categoryRepository.save(category);
    }

    @Override
    public String deleteCategory(Long categoryId) {
        //Caterogy is being searched by findById method of Jparepository
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Resoirce not found!!"));
        categoryRepository.delete(category);
        return "Category deleted!";
    }

    @Override
    public Category updateCategory(Category category, Long categoryId) {
        //Caterogy is being searched by findById method of Jparepository
        Category savedCategory = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Resource not found!!"));

        savedCategory = categoryRepository.save(category);
        return savedCategory;
    }
}
