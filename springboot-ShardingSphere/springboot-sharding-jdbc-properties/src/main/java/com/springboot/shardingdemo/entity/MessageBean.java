
package com.springboot.shardingdemo.entity;

import com.vladmihalcea.hibernate.type.json.JsonStringType;
import lombok.Data;

import javax.persistence.*;


import lombok.Data;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import java.util.Date;

/**
 * @Auther: huhy
 * @Date: 2020/7/15 16:36
 * @Description:
 */

@Entity
@Table(name = "message")
@Data
@TypeDef(name = "json", typeClass = JsonStringType.class)
public class MessageBean {

    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "id")
    protected Long id = -1L;

    @CreatedBy
    @Column(name = "create_by")
    protected Long createBy; // 创建者

    @CreatedDate
    @Column(name = "create_date")
    protected Date createDate; // 创建时间

    @LastModifiedBy
    @Column(name = "update_by")
    protected Long updateBy; // 更新者

    @LastModifiedDate
    @Column(name = "update_date")
    protected Date updateDate; // 更新时间

    @Column(name = "send_time")
    private Date sendTime;// 发件时间

    @Column(name = "addressor_id")
    private Long addressorId;// 发件人id

    @Column(name = "recipient_id")
    private Long recipientId;// 收件人id

    @Column(name = "source_system")
    private String sourceSystem; // 系统来源

    @Column(name = "send_way")
    private String sendWay;  //发送方式

    @Column(name = "message_status")
    private String messagestatus;  //消息状态:是否已读

    @Column(name = "is_delete")
    private String isDelete;// 是否删除

    @Column(name = "message_type")
    private String messageType;//消息类别

}

