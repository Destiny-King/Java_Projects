package com.wym.carousel;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = "com.wym.carousel.mapper")
public class CarouselApplication {
	public static void main(String[] args) {
		SpringApplication.run(CarouselApplication.class, args);
	}
}
