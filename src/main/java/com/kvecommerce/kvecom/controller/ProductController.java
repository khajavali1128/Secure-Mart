package com.kvecommerce.kvecom.controller;

import com.kvecommerce.kvecom.model.Product;
import com.kvecommerce.kvecom.payload.ProductDTO;
import com.kvecommerce.kvecom.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class ProductController {

    @Autowired
    ProductService productService;

    @PostMapping("/admin/categories/{categoryId}/product")
    public ResponseEntity<ProductDTO> addProduct(@RequestBody Product product,
                                          @PathVariable Long categoryId) {
    ProductDTO productDTO = productService.addProduct(categoryId, product);
    return new ResponseEntity<>(productDTO, HttpStatus.CREATED);
    }
}
