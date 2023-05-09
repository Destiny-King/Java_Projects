package com.wym;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.wym.mapper")
public class MyFavoriteApplication {

	public static void main(String[] args) {
		SpringApplication.run(MyFavoriteApplication.class, args);
	}
}
