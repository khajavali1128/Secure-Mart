package com.kvecommerce.kvecom.service;

import com.kvecommerce.kvecom.exceptions.ResourceNotFoundException;
import com.kvecommerce.kvecom.model.Category;
import com.kvecommerce.kvecom.model.Product;
import com.kvecommerce.kvecom.payload.ProductDTO;
import com.kvecommerce.kvecom.repositories.CategoryRepository;
import com.kvecommerce.kvecom.repositories.ProductRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class ProductServiceImp implements ProductService{
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public ProductDTO addProduct(Long categoryId, Product product) {
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Category", "id", categoryId));
        product.setImage("default.png");
        product.setCategory(category);
        double specialPrice =
                product.getProductPrice() -
                        ((product.getDiscount() * 0.01) * product.getProductPrice());
        product.setSpecialPrice(specialPrice);
        Product savedProduct = productRepository.save(product);

        return modelMapper.map(savedProduct, ProductDTO.class);
    }
}
