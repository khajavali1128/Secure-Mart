package com.securemart.service;

import com.securemart.exceptions.APIException;
import com.securemart.exceptions.ResourceNotFoundException;
import com.securemart.model.Category;
import com.securemart.payload.CategoryDTO;
import com.securemart.payload.CategoryResponse;
import com.securemart.repository.CategoryRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class categoryServiceImpl implements categoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ModelMapper modelMapper;

    /*Begin ------ get Categories */
    @Override
    public CategoryResponse getAllCategories(Integer pageNo, Integer limit, String sortField, String sortDirection) {
        Sort sorted = sortDirection.equalsIgnoreCase("asc")
                ? Sort.by(sortField).ascending()
                : Sort.by(sortField).descending();

        Pageable pageDetails = PageRequest.of(pageNo, limit, sorted);
        Page<Category> categoryPage = categoryRepository.findAll(pageDetails);
        List<Category> categories = categoryPage.getContent();
        if(categories.isEmpty())
            throw  new APIException("No Categories found!!");

        List<CategoryDTO> categoryDTOs = categories.stream()
                .map(category -> modelMapper.map(category, CategoryDTO.class))
                .toList();

        CategoryResponse categoryResponse = new CategoryResponse();
        categoryResponse.setContent(categoryDTOs);
        categoryResponse.setPageNo(categoryPage.getNumber());
        categoryResponse.setPageSize(categoryPage.getSize());
        categoryResponse.setTotalPages(categoryPage.getTotalPages());
        categoryResponse.setTotalElements(categoryPage.getTotalElements());
        categoryResponse.setLastPage(categoryPage.isLast());
        return categoryResponse;
    }
    /* End ------ get Categories */

    /*Begin ------ Create Category */
    @Override
    public CategoryDTO createCategory(CategoryDTO categoryDTO) {

        Category category = modelMapper.map(categoryDTO, Category.class);
        Category savedCategory = categoryRepository.findByCategoryName(category.getCategoryName());
        if(savedCategory != null) {
            throw new APIException("Category with "+ savedCategory.getCategoryName()+" already exists");
        }
        Category newCategory = categoryRepository.save(category);

        return modelMapper.map(newCategory, CategoryDTO.class);
    }
    /* End ------ get Create Category */

    /*Begin ------Delete Category */
    @Override
    public CategoryDTO deleteCategory(Long categoryId) {

        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException("Category", "id", categoryId));
        categoryRepository.delete(category);

        return modelMapper.map(category, CategoryDTO.class);
    }
    /*End ------Delete Category */

    /*Begin ------Update Category */
    @Override
    public CategoryDTO updateCategory(CategoryDTO categoryDTO, Long categoryId) {

        Category category = modelMapper.map(categoryDTO, Category.class);
        categoryRepository.findById(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException("Category", "id", categoryId));

        category.setCategoryId(categoryId);
        Category updatedCategory = categoryRepository.save(category);

        return modelMapper.map(updatedCategory, CategoryDTO.class);
    }
    /*End ------Update Category */
}
