package com.kvecommerce.kvecom.controller;

import com.kvecommerce.kvecom.configurations.AppConstants;
import com.kvecommerce.kvecom.payload.CategoryDTO;
import com.kvecommerce.kvecom.payload.CategoryResponse;
import com.kvecommerce.kvecom.service.categoryService;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class CategoryController {

    @Autowired
    public categoryService categoryService;
    private ModelMapper modelMapper;

    @GetMapping("/public/categories")
    public ResponseEntity<CategoryResponse> getCategories(
            @RequestParam(name="pageNo", defaultValue = AppConstants.PageNumber, required = false) Integer pageNo,
            @RequestParam(name="pageSize", defaultValue = AppConstants.PageSize, required = false) Integer pageSize,
            @RequestParam(name="sortBy", defaultValue = AppConstants.SortBy, required = false) String sortBy,
            @RequestParam(name="sortOrder", defaultValue = AppConstants.SortOrder, required = false) String sortOrder) {

        CategoryResponse categories = categoryService.getAllCategories(pageNo, pageSize, sortBy, sortOrder);
        return new ResponseEntity<>(categories, HttpStatus.OK);
    }

    @PostMapping("/public/categories")
    public ResponseEntity<String> setCategories(@Valid @RequestBody CategoryDTO categoryDTO) {

        CategoryDTO createdCategoryDTO = categoryService.createCategory(categoryDTO);
        return new ResponseEntity<>("Category Created successfully with Id "+createdCategoryDTO.getCategoryId(),HttpStatus.CREATED);
    }

    @DeleteMapping("/admin/categories/{categoryId}")
    public ResponseEntity<CategoryDTO> deleteCategory(@PathVariable Long categoryId) {
        CategoryDTO deletedCategoryDTO = categoryService.deleteCategory(categoryId);
        return new ResponseEntity<>(deletedCategoryDTO, HttpStatus.OK);
    }

    @PutMapping("/public/categories/{categoryId}")
    public ResponseEntity<CategoryDTO> updateCategories(@Valid @RequestBody CategoryDTO categoryDTO,
                                                   @PathVariable Long categoryId) {
        CategoryDTO updatedCategoryDTO = categoryService.updateCategory(categoryDTO, categoryId);
        return new ResponseEntity<>(updatedCategoryDTO, HttpStatus.OK);
    }

}
