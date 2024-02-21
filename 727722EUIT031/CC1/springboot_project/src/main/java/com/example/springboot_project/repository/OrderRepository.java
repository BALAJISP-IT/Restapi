package com.example.springboot_project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.springboot_project.model.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    
}
