package com.magician.book;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

//定时器开启自带定时器
@EnableScheduling
//开始事物管理
@EnableTransactionManagement
//开启mabatis Dao扫描
@MapperScan("com.magician.book.dao")
//开启spring注册扫描
@ComponentScan("com")
@SpringBootApplication
public class ApplicationRunning {
    public static void main(String[] args) {
        SpringApplication.run(ApplicationRunning.class, args);
        System.out.println("启动完成");
    }
}
