package com.wym.blog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BlogApp {
	public static void main(String[] args) {
		SpringApplication.run(BlogApp.class, args);
	}
}

/**
 * B/S架构
 * 客户端Client：浏览器（前端技术：H5+CSS3+JavaScript）
 * 服务器Server：Web中间件服务器（SSM：SpringMVC控制器+Spring业务层+Mybatis持久层）、数据库（MySQL）
 * entity实体层（存放实体类，实现set和get的方法）、mapper层（对数据库进行持久化操作）
 * service业务层（给controller层的类提供接口进行调用）、controller控制层（负责具体模块的业务流程控制）
 */