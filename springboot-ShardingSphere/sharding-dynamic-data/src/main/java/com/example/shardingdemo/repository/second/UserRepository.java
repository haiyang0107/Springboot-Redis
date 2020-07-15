package com.example.shardingdemo.repository.second;

import com.example.shardingdemo.entity.second.UserBean;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<UserBean,Long> {
}
