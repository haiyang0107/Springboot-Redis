package com.springboot.configdemo.service;

import com.springboot.configdemo.dao.UserMapper;
import com.springboot.configdemo.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserMapper mapper;
    public List<User> findList() {
        return mapper.findList();
    }
}
