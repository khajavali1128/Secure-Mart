package com.kvecommerce.kvecom.service;

import com.kvecommerce.kvecom.model.Product;
import com.kvecommerce.kvecom.payload.ProductDTO;
import org.springframework.stereotype.Service;

public interface ProductService {
    ProductDTO addProduct(Long categoryId, Product product);
}
