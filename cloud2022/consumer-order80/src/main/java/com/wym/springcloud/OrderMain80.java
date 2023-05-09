package com.wym.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/*1、新建模块
  2、改POM
  3、写YML
  4、主启动
  5、业务类
  6、测试*/

@SpringBootApplication
@EnableEurekaClient
public class OrderMain80 {
	public static void main(String[] args) {
		SpringApplication.run(OrderMain80.class, args);
	}
}
