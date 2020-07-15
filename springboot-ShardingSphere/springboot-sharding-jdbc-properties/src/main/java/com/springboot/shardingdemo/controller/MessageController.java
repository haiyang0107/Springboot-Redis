package com.springboot.shardingdemo.controller;

import com.springboot.shardingdemo.entity.MessageBean;
import com.springboot.shardingdemo.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @Auther: huhy
 * @Date: 2020/7/15 16:36
 * @Description:
 */
@RestController
@RequestMapping("/message")
public class MessageController {

    @Autowired
    private MessageService messageService;


    @RequestMapping(value = "/findAll", produces = "application/json", method = RequestMethod.POST)
    public List<MessageBean> findAll() {
        return messageService.findAll();
    }

    @RequestMapping(value = "/addMessage", produces = "application/json", method = RequestMethod.POST)
    public MessageBean addMessage(@RequestBody MessageBean entity) {
        return messageService.addMessage(entity);
    }

    @RequestMapping(value = "/editMessage", produces = "application/json", method = RequestMethod.POST)
    public MessageBean editMessage(@RequestBody MessageBean messageBeanEntity) {
        return messageService.editMessage(messageBeanEntity);
    }

    @RequestMapping(value = "/findById", produces = "application/json", method = RequestMethod.POST)
    public MessageBean findById(@RequestBody Map<String, String> map) {
        String Id = map.get("id");
        long id = Long.parseLong(Id);
        return messageService.findById(id);
    }

    @RequestMapping(value = "/delMessage", produces = "application/json", method = RequestMethod.POST)
    public Boolean delMessage(@RequestBody Map<String, String> map) {
        String Id = map.get("id");
        long id = Long.parseLong(Id);
        return messageService.delById(id);
    }


}
