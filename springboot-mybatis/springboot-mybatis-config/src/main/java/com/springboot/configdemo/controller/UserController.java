package com.springboot.configdemo.controller;

import com.springboot.configdemo.entity.master.User;
import com.springboot.configdemo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @RequestMapping(value = "/insert", produces = "application/json", method = RequestMethod.POST)
    public User insert(@RequestBody User user){
        return userService.insertUser(user);
    }

    @RequestMapping(value = "/delete", produces = "application/json", method = RequestMethod.POST)
    public boolean delete(@RequestParam int id){
        return userService.deleteUser(id);
    }

}
