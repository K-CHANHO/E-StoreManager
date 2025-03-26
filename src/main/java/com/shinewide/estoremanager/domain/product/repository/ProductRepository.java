package com.shinewide.estoremanager.domain.product.repository;

import com.shinewide.estoremanager.domain.product.Category;
import com.shinewide.estoremanager.domain.product.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByCategory(Category category);
}
