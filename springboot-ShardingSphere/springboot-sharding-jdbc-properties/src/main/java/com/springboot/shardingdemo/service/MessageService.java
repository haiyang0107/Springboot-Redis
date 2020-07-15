package com.springboot.shardingdemo.service;

import com.springboot.shardingdemo.entity.MessageBean;
import com.springboot.shardingdemo.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * @Auther: huhy
 * @Date: 2020/7/15 16:36
 * @Description:
 */
@Service
@Transactional(readOnly = true)
public class MessageService {
    @Autowired
    private MessageRepository messageRepository;


    // 分页查询消息列表
    @Transactional(readOnly = true)
    public List<MessageBean> findAll() {
     return messageRepository.findAll();
    }
    //添加消息
    @Transactional(readOnly = false)
    public MessageBean addMessage(MessageBean entity) {
       return messageRepository.save(entity);
    }
    //编辑消息
    @Transactional(readOnly = false)
    public MessageBean editMessage(MessageBean entity){
        return messageRepository.save(entity);
    }
    //查看消息详情
    @Transactional(readOnly = true)
    public MessageBean findById(long id) {
        return messageRepository.findById(id).get();
    }
    //删除消息
    @Transactional(readOnly = false)
    public Boolean delById(long id) {
        Optional<MessageBean> byId = messageRepository.findById(id);
        if (byId.isPresent()){
            MessageBean messageBean = byId.get();
            messageBean.setIsDelete("已删除");
            try {
                messageRepository.save(messageBean);
                return true;
            } catch (Exception e) {
                return false;
            }
        }
        return false;
    }

}
