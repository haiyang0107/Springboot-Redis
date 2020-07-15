package com.example.shardingdemo.service.first;

import com.example.shardingdemo.entity.first.DictBean;
import com.example.shardingdemo.repository.first.DictRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DictService {
    @Autowired
    private DictRepository repository;
    public List<DictBean> list() {
        return (List<DictBean>) repository.findAll();
    }
}
