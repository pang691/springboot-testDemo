package com.taikang.health.iams;

import com.taikang.health.iams.supplier.AuthcByOauth2;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication(scanBasePackages = {"com.taikang.health"})
@MapperScan(basePackages = {"com.taikang.health.**.access"})
public class IamsApplication {

	public static void main(String[] args) {
		SpringApplication.run(IamsApplication.class, args);
	}

//	@Bean
//	public Authenticator authcAutoSelector() {
//		return new AuthcByOauth2();
//	}

}