package com.example.springboot_project.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.springboot_project.model.User;
import com.example.springboot_project.repository.UserRepo;

@Service
public class UserService {
    @Autowired
    UserRepo obj;
    //create
    public User postUser(User newUser)
    {
        return obj.save(newUser);
    }
    //read
    public User getUserbyName(String name)
    {
        return obj.findByUserName(name);
    }
    public List<User> getUsers()
    {
        return obj.findAll();
    }
    public Optional<User> getUserbyId(Long id)
    {
        return obj.findById(id);
    }
    public List<User> getUserBySubscription(String subscription)
    {
        return obj.findBySubscription(subscription);
    }
    //update
    public User updateUser(Long Id,User user)
    {
        User u1=obj.findById(Id).orElse(null);
        if(u1!=null)
        {
            u1.setAddress(user.getAddress());
            u1.setMobile(user.getMobile());
            u1.setPassword(user.getPassword());
            u1.setSubscription(user.getSubscription());
            u1.setUserName(user.getUserName());
            return obj.saveAndFlush(u1);
        }
        else{
            return null;
        }
    }
    public String updatePassword(String oldPass,String newPass,Long Id)
    {
          User user=obj.findById(Id).orElse(null);
           if(user!=null&&user.getPassword().equals(oldPass))
           {
            user.setPassword(newPass);
             obj.saveAndFlush(user);
             return "password updated successfully";
           }
           else{
               return "Id or password is wrong";
           }
    }
    //delete
    public String deleteById(Long id)
    {
        if(obj.existsById(id))
        {
            obj.deleteById(id);
            return "User deleted successfully";
        }
        else{
            return "User not found";
        }
    }

}
