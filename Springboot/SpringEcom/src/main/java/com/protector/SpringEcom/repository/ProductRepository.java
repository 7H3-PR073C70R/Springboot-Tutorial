package com.protector.SpringEcom.repository;

import com.protector.SpringEcom.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
    @Query("SELECT product FROM Product product WHERE " +
            "LOWER(product.name) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
            "LOWER(product.description) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
            "LOWER(product.brand) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
            "LOWER(product.category) LIKE LOWER(CONCAT('%', :keyword, '%'))")
    List<Product> searchProducts(String keyword);
}
