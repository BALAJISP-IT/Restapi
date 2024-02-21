package com.example.springboot_project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.springboot_project.model.Order;
import com.example.springboot_project.service.OrderService;

import java.util.List;
import java.util.Optional;

@RestController
public class OrderController {
    
    @Autowired
    private OrderService orderService;
    
    @GetMapping("/getorders")
    public ResponseEntity<List<Order>> getAllOrders() {
        List<Order> orders = orderService.getAllOrders();
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }

    @GetMapping("/getorder/{id}")
public ResponseEntity<Order> getOrderById(@PathVariable Long id) {
    Optional<Order> order = orderService.getOrderById(id);
    if (order.isPresent()) {
        return ResponseEntity.ok().body(order.get());
    } else {
        return ResponseEntity.notFound().build();
    }
}


    @PostMapping("/postorder")
    public ResponseEntity<Order> createOrder(@RequestBody Order order) {
        Order newOrder = orderService.createOrder(order);
        return new ResponseEntity<>(newOrder, HttpStatus.CREATED);
    }

    @PutMapping("updateorder/{id}")
    public ResponseEntity<Order> updateOrder(@PathVariable Long id, @RequestBody Order order) {
        Order updatedOrder = orderService.updateOrder(id, order);
        if (updatedOrder != null) {
            return new ResponseEntity<>(updatedOrder, HttpStatus.OK);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("deleteorder/{id}")
    public ResponseEntity<String> deleteOrder(@PathVariable Long id) {
        orderService.deleteOrder(id);
        return ResponseEntity.ok().body("Deleted succesfully");
    }
}
