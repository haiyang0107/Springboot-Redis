package com.springboot.beetlsqldemo.entity;

import lombok.Data;
import org.beetl.sql.annotation.entity.AssignID;
import org.beetl.sql.annotation.entity.Table;

@Table(name="sys_user")
@Data
public class UserInfo {
    @AssignID
    private Long id;
    private String name;
}
