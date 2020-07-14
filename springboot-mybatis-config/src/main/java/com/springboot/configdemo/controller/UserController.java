package com.springboot.configdemo.controller;

import com.springboot.configdemo.entity.User;
import com.springboot.configdemo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/user")
public class UserController {
    @Autowired
    UserService userService;

    @RequestMapping(value = "/list")
    public List<User> list(){
        return userService.findList();
    }
}
