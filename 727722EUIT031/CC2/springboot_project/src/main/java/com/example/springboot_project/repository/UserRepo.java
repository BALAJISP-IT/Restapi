package com.example.springboot_project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.springboot_project.model.User;

import jakarta.transaction.Transactional;

import java.util.List;

@Repository
public interface UserRepo extends JpaRepository<User,Long>{
    User findByUserName(String userName);
     List<User> findBySubscription(String subscription);
    
   
    // @Query("UPDATE user e SET e.password=:newpass WHERE e.user_name=:name AND e.password=:oldpass")
    // void updatePassword(@Param("newpass") String newpass,@Param("name") String name,@Param("oldpass") String oldpass);
}
