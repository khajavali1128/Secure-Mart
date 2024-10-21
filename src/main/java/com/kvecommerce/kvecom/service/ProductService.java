package com.kvecommerce.kvecom.service;

import com.kvecommerce.kvecom.model.Product;
import com.kvecommerce.kvecom.payload.ProductDTO;
import com.kvecommerce.kvecom.payload.ProductResponse;
import org.springframework.stereotype.Service;

public interface ProductService {
    ProductDTO addProduct(Long categoryId, Product product);

    ProductResponse getAllProducts();

    ProductResponse searchByCategory(Long categoryId);
}
