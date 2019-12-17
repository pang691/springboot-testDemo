package com.taikang.health.iams;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@MapperScan(basePackages = {"com.taikang.***.iams.dao"})
public class IamsApplication {

	public static void main(String[] args) {
		SpringApplication.run(IamsApplication.class, args);
	}

//	@Bean
//	public Authenticator authcAutoSelector() {
//		return new AuthcByOauth2();
//	}

}