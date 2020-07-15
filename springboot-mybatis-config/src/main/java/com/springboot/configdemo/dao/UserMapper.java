package com.springboot.configdemo.dao;

import com.springboot.configdemo.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface  UserMapper {

    public List<User> findList();

    /**
     * mybatis insert 返回结果只有-1，0和1
     * @param user
     * @return
     */
    public  int insertUser(User user);
}
