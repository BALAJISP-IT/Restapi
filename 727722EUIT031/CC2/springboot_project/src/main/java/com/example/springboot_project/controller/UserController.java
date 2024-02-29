package com.example.springboot_project.controller;

import org.springframework.web.bind.annotation.RestController;

import com.example.springboot_project.service.UserService;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import com.example.springboot_project.model.User;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;



@RestController
public class UserController {
    @Autowired
    UserService obj;
        @GetMapping("/getusers")
        public ResponseEntity<List<User>> getAllUsers() {
            try {
                List<User> users = obj.getUsers();
                return new ResponseEntity<>(users, HttpStatus.OK);
            } catch (Exception e) {
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
    
        @GetMapping("/getuserbyid/{id}")
        public ResponseEntity<User> getUserById(@PathVariable Long id) {
            try {
                Optional<User> user = obj.getUserbyId(id);
                if (user.isPresent()) {
                    return ResponseEntity.ok().body(user.get());
                } else {
                    return ResponseEntity.notFound().build();
                }
            } catch (Exception e) {
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
        
        @GetMapping("/getuserbyname/{name}")
        public ResponseEntity<User> getUserByName(@PathVariable String name) {
            try {
                User user = obj.getUserbyName(name);
                return ResponseEntity.ok().body(user);
            } catch (Exception e) {
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
    
        @GetMapping("/getuserbysubscription/{subscription}")
        public ResponseEntity<List<User>> getUsersBySubscription(@PathVariable String subscription) {
            try {
                List<User> users = obj.getUserBySubscription(subscription);
                return ResponseEntity.ok().body(users);
            } catch (Exception e) {
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
    
        @PostMapping("/postuser")
        public ResponseEntity<User> postUser(@RequestBody User entity) {
            try {
                User newUser = obj.postUser(entity);
                return new ResponseEntity<>(newUser, HttpStatus.CREATED);
            } catch (Exception e) {
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
    
        @PutMapping("/updateuser/{id}")
        public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User entity) {
            try {
                User updatedUser = obj.updateUser(id, entity);
                return new ResponseEntity<>(updatedUser, HttpStatus.OK);
            } catch (Exception e) {
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
    
        @PutMapping("/updatepassword")
        public ResponseEntity<String> updatePassword(@RequestParam Long id, @RequestParam String oldpass, @RequestParam String newpass) {
            try {
                String message = obj.updatePassword(oldpass, newpass, id);
                return new ResponseEntity<>(message, HttpStatus.OK);
            } catch (Exception e) {
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
    
        @DeleteMapping("/deleteuser/{id}")
        public ResponseEntity<String> deleteUserById(@PathVariable Long id) {
            try {
                String message = obj.deleteById(id);
                return new ResponseEntity<>(message, HttpStatus.OK);
            } catch (Exception e) {
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
    }

