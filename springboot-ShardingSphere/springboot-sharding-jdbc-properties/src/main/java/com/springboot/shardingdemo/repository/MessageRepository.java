
package com.springboot.shardingdemo.repository;

import com.springboot.shardingdemo.entity.MessageBean;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RestController;


/**
 * @Auther: huhy
 * @Date: 2020/7/15 16:36
 * @Description:
 */
@Repository
public interface MessageRepository extends JpaRepository<MessageBean,Long> {

}
