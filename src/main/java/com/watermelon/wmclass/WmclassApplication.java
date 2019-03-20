package com.watermelon.wmclass;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@MapperScan("com.watermelon.wmclass.mapper")
//开启事务管理
@EnableTransactionManagement
public class WmclassApplication {

    public static void main(String[] args) {
        SpringApplication.run(WmclassApplication.class, args);
    }
}
