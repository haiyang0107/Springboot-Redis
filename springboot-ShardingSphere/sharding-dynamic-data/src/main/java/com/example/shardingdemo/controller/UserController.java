package com.example.shardingdemo.controller;

import com.example.shardingdemo.entity.second.UserBean;
import com.example.shardingdemo.service.second.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService service;

    @RequestMapping(value = "/list")
    public List<UserBean> list(){
        return service.list();
    }
}
