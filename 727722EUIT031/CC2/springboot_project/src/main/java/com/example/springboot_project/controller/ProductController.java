package com.example.springboot_project.controller;

import org.springframework.web.bind.annotation.RestController;

import com.example.springboot_project.model.Product;
import com.example.springboot_project.service.ProductService;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;




@RestController
public class ProductController {
    @Autowired
    ProductService obj;
    @GetMapping("/getproducts")
    public ResponseEntity<List<Product>> getMethodName() {
        try {
            List<Product> products = obj.getAllProducts();
            return new ResponseEntity<>(products, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/getproduct/{Id}")
    public ResponseEntity<Product> getProductById(@PathVariable Long Id) {
        try {
            Optional<Product> product = obj.getProductById(Id);
            if (product.isPresent()) {
                return ResponseEntity.ok().body(product.get());
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @PostMapping("/postproduct")
    public ResponseEntity<Product> postMethodName(@RequestBody Product entity) {
        //TODO: process POST request
        
        try {
            Product newProduct = obj.postProduct(entity);
            return new ResponseEntity<>(newProduct, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PutMapping("/updateproduct/{id}")
    public ResponseEntity<Product> putMethodName(@PathVariable Long id, @RequestBody Product entity) {
        //TODO: process PUT request
        
        try {
            Product updatedProduct = obj.updateProduct(id, entity);
            return new ResponseEntity<>(updatedProduct, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }    }
    @GetMapping("/getproductbyname")
    public ResponseEntity<Product> getProductByName(@RequestParam String name) {
        try {
            Product product = obj.getProductByName(name);
            return new ResponseEntity<>(product, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/deleteproduct/{id}")
    public ResponseEntity<String> deleteProductById(@PathVariable Long id) {
        try {
            String message = obj.DeleteById(id);
            return new ResponseEntity<>(message, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    


    
    
    
    
}
