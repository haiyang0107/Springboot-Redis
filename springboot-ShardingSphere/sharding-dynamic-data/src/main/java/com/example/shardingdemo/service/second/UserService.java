package com.example.shardingdemo.service.second;

import com.example.shardingdemo.entity.first.DictBean;
import com.example.shardingdemo.entity.second.UserBean;
import com.example.shardingdemo.repository.second.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository repository;

    public List<UserBean> list() {
        return (List<UserBean>)repository.findAll();
    }
}
