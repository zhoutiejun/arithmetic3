package com.model.arithmetic3;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages ={"com.model.arithmetic3.dao.mapper"})
public class Arithmetic3Application {

	public static void main(String[] args) {
		SpringApplication.run(Arithmetic3Application.class, args);
	}

}
