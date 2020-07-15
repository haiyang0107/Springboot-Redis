package com.example.springbootmybatisdemo.dao;

import com.example.springbootmybatisdemo.model.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface UserDao {
    @Select("select * from t_user")
    List<User> getUserList();
}
