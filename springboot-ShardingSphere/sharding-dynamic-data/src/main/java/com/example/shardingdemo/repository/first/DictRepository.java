package com.example.shardingdemo.repository.first;

import com.example.shardingdemo.entity.first.DictBean;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DictRepository extends CrudRepository<DictBean,Long> {
}
