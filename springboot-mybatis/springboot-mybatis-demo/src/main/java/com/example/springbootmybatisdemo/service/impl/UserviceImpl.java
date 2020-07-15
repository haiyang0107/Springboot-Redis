package com.example.springbootmybatisdemo.service.impl;

import com.example.springbootmybatisdemo.dao.UserDao;
import com.example.springbootmybatisdemo.model.User;
import com.example.springbootmybatisdemo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UserviceImpl implements UserService {
    @Resource
    private UserDao dao;
    @Override
    public List<User> getUserList() {
        return dao.getUserList();
    }
}
