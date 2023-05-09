package com.wym.blog.controller;

import com.wym.blog.service.LoginService;
import com.wym.blog.vo.Result;
import com.wym.blog.vo.params.LoginParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("register")
public class RegisterControllert {

	@Autowired
	private LoginService loginService;

	@PostMapping
	public Result register(@RequestBody LoginParam loginParam) {
		//sso 单点登录
		return loginService.register(loginParam);
	}
}
