package com.example.func;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceAutoConfigure;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication(exclude = DruidDataSourceAutoConfigure.class)
@MapperScan({"com.example.func.local.mapper"})
@EnableFeignClients
public class FuncUtilsApplication {

    public static void main(String[] args) {
        SpringApplication.run(FuncUtilsApplication.class, args);
    }

}
