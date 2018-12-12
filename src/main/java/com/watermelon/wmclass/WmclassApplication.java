package com.watermelon.wmclass;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.watermelon.wmclass.mapper")
public class WmclassApplication {

    public static void main(String[] args) {
        SpringApplication.run(WmclassApplication.class, args);
    }
}
