package com.springboot.configdemo.service;

import com.springboot.configdemo.dao.second.DictMapper;
import com.springboot.configdemo.entity.master.User;
import com.springboot.configdemo.entity.second.Dict;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DictService {
    @Autowired
    private DictMapper mapper;
    public List<Dict> findList() {
        return mapper.findList();
    }

    public Dict insertDict(Dict dict) {
        int i = mapper.insertDict(dict);
        if(i<= 0){
            return  null;
        }
        return  dict;
    }

    public boolean deleteDict(int id) {
        int i = mapper.deleteDict(id);
        if(i <= 0 ){
            return  false;
        }
        return true;
    }
}
