package com.taikang.test;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication(scanBasePackages = {"com.taikang.test"})
@MapperScan(basePackages = {"com.taikang.test.scheduled.dao"})
@EnableSwagger2
@EnableAsync
public class FileUpLoadApp {
    public static void main(String[] args) {
        SpringApplication.run(FileUpLoadApp.class,args);
    }
}
