package com.springboot.configdemo.service;

import com.springboot.configdemo.dao.second.DictMapper;
import com.springboot.configdemo.entity.master.User;
import com.springboot.configdemo.entity.second.Dict;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)//开启事务注解
public class DictService {
    @Autowired
    private DictMapper mapper;
    public List<Dict> findList() {
        return mapper.findList();
    }
    @SneakyThrows
    @Transactional(readOnly = false)
    public Dict insertDict(Dict dict) {
        int i = mapper.insertDict(dict);
        System.out.println("确认插入数据条数为：》》》》"+i);
        if(i<= 0){
            return  null;
        }
        return  dict;
    }
    @Transactional(readOnly = false)
    public boolean deleteDict(int id) {
        int i = mapper.deleteDict(id);
        if(i <= 0 ){
            return  false;
        }
        return true;
    }
}
