package com.example.springboot_project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.springboot_project.model.Order;

import jakarta.transaction.Transactional;

@Repository
@Transactional
public interface OrderRepository extends JpaRepository<Order, Long> {
    @Modifying
    @Query(value = "INSERT INTO orders(product_name,quantity,price,user_id) VALUES(?1,?2,?3,?4) ",nativeQuery = true)
    void postOrder(String productName,int quantity,double price,long id );
    
}
