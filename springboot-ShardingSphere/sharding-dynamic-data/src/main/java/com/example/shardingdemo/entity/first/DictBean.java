
package com.example.shardingdemo.entity.first;

import com.alibaba.fastjson.JSONObject;
import com.vladmihalcea.hibernate.type.json.JsonStringType;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;



import lombok.Data;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import java.util.Date;

/**
 * @Auther: huhy
 * @Date: 2020/7/15 17:19
 * @Description:
 */

@Entity
@Table(name = "t_dict")
@Data
public class DictBean implements Serializable{


    @Id
    @Column(name = "id")
    private Long id;

    @Column(name = "type")
    private String type;

    @Column(name = "value")
    private String value;



}

