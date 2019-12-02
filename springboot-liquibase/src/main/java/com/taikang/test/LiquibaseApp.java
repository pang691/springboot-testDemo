package com.taikang.test;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.taikang.test.sqltest.mapper")
public class LiquibaseApp {
    public static void main(String[] args) {
        SpringApplication.run(LiquibaseApp.class,args);
    }
}
