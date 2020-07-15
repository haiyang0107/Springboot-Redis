package com.springboot.configdemo.controller;

import com.springboot.configdemo.entity.second.Dict;
import com.springboot.configdemo.service.DictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/dict")
public class DictController {

    @Autowired
    private DictService service;

    @RequestMapping(value = "/list")
    public List<Dict> list(){
        return service.findList();
    }

    @RequestMapping(value = "/insert", produces = "application/json", method = RequestMethod.POST)
    public Dict insert(@RequestBody Dict dict){
        return service.insertDict(dict);
    }

    @RequestMapping(value = "/delete", produces = "application/json", method = RequestMethod.POST)
    public boolean delete(@RequestParam int id){
        return service.deleteDict(id);
    }
}
