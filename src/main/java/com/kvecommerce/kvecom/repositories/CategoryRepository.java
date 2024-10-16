package com.kvecommerce.kvecom.repositories;

import com.kvecommerce.kvecom.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    Category findByCategoryName(String category);
}
