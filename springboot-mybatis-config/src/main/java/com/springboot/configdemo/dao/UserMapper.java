package com.springboot.configdemo.dao;

import com.springboot.configdemo.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface  UserMapper {

    public List<User> findList();
}
