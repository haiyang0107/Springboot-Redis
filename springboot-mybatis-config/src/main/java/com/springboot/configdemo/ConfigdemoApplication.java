package com.springboot.configdemo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan(basePackages = "com.springboot.configdemo.dao")
@SpringBootApplication
public class ConfigdemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(ConfigdemoApplication.class, args);
    }

}
