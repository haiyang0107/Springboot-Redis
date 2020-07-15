package com.springboot.configdemo.service;

import com.springboot.configdemo.dao.master.UserMapper;
import com.springboot.configdemo.entity.master.User;
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

    public User insertUser(User user) {

        int i = mapper.insertUser(user);
        if(i<=0){
            return null;
        }
        return user;
    }

    public boolean deleteUser(int id) {
            int i = mapper.deleteUser(id);
            if(i<= 0){
                return false;
            }
            return true ;
    }
}
