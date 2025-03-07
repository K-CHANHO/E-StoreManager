package com.shinewide.estoremanager.repository;

import com.shinewide.estoremanager.domain.Product;
import com.shinewide.estoremanager.domain.Product.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByCategory(Category category);
}
