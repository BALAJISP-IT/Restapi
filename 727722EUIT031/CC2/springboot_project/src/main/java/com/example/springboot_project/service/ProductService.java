package com.example.springboot_project.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.springboot_project.model.Product;
import com.example.springboot_project.repository.ProductRepo;

@Service
public class ProductService {
    @Autowired
    ProductRepo obj;
    //read
    public List<Product> getAllProducts()
    {
        return obj.findAll();
    }
    public Optional<Product> getProductById(Long Id)
    {
        return obj.findById(Id);
    }
    //custom query
    public Product getProductByName(String productName)
    {
        return obj.findByProductName(productName);
    }
    //create
    public Product postProduct(Product newProduct)
    {
        return obj.save(newProduct);
    }
    //update
    public Product updateProduct(Long Id,Product product)
    {
        Product p1=obj.findById(Id).orElse(null);
       if(p1!=null)
       {
            p1.setPrice(product.getPrice());
            p1.setProductName(product.getProductName());
            p1.setQuantity(product.getQuantity());
            p1.setType(product.getType());
            return obj.saveAndFlush(p1);
       }
       else{
        return null;
       }
    }
    public Product updatePrice(Long Id,float price)
    {
           Product p1=obj.findById(Id).orElse(null);
           if(p1!=null)
           {
            p1.setPrice(price);
            return obj.saveAndFlush(p1);
           }
           else{
            return null;
           }
        }
    //delete
    public String DeleteById(Long Id)
    {
        if(obj.existsById(Id))
        {
            obj.deleteById(Id);
            return "Deleted Successfully";
        }
        return "Product Not Found";
    }
    
}
