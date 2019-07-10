package com.example.springbootmybatisdemo.controller;

import com.example.springbootmybatisdemo.model.User;
import com.example.springbootmybatisdemo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController  {
    @Autowired
    UserService userService;

    @GetMapping("list")
     public List<User>  getUserList(){
         return userService.getUserList();
     }
}
