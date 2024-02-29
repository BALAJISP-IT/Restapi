package com.example.springboot_project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.springboot_project.model.Order;
import com.example.springboot_project.repository.OrderRepository;
import com.example.springboot_project.repository.UserRepo;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;
@Autowired
    UserRepo userRepo;
    public List<Order> getAllOrders(int pageNo,int pageSize,String field) {
        Pageable page=PageRequest.of(pageNo,pageSize,Sort.by(Sort.Direction.ASC,field));
        return orderRepository.findAll(page).getContent();
    }

    public Optional<Order> getOrderById(Long id) {
        return orderRepository.findById(id);
    }
public void postOrders(List<Order> orders,long id)
{
    if(userRepo.existsById(id))
    {
        System.out.println("Register First");
    }
   for (Order order : orders) {
    orderRepository.postOrder(order.getProductName(), order.getQuantity(), order.getPrice(), id);

   }
}
    public String createOrder(Order order,long id) {
        if(userRepo.existsById(id))
        {
            try {
                
                orderRepository.postOrder(order.getProductName(), order.getQuantity(), order.getPrice(), id);
            } catch (Exception e) {
                // TODO: handle exception
                System.out.println(e);
            }
            return "problem in query";
        }
        else{
            return "user not found";
        }
    }

    public Order updateOrder(Long id, Order order) {
        Order existingOrder = orderRepository.findById(id).orElse(null);
        if (existingOrder!=null) {
           
            existingOrder.setProductName(order.getProductName());
            existingOrder.setQuantity(order.getQuantity());
            existingOrder.setPrice(order.getPrice());
            return orderRepository.saveAndFlush(existingOrder);
        } else {
            return null; // Handle error
        }
    }

    public void deleteOrder(Long id) {
        orderRepository.deleteById(id);
    }
}

