package com.taikang.health;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = {"com.taikang.health.**.dao"})
public class IamsApplication {

	public static void main(String[] args) {
		SpringApplication.run(IamsApplication.class, args);
	}

//	@Bean
//	public Authenticator authcAutoSelector() {
//		return new AuthcByOauth2();
//	}

}