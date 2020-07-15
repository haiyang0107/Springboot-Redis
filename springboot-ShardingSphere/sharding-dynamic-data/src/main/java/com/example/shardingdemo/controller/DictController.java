package com.example.shardingdemo.controller;

import com.example.shardingdemo.entity.first.DictBean;
import com.example.shardingdemo.service.first.DictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/dict")
public class DictController {
    @Autowired
    private DictService service;

    @RequestMapping(value = "/list")
    public List<DictBean> list(){
        return service.list();
    }

}
