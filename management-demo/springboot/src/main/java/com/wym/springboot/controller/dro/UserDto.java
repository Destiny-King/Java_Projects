package com.wym.springboot.controller.dro;

import lombok.Data;

/**
 * Created by romin on 2023/7/11.
 * 接收前端登录请求参数
 */
@Data
public class UserDto {
	private String username;
	private String password;
}
