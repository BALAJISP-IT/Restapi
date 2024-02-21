package com.example.springboot_project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.springboot_project.model.Product;
import java.util.List;

@Repository
public interface ProductRepo extends JpaRepository<Product,Long>{
    Product findByProductName(String productName);
}
