package com.wym.blog.controller;

import com.wym.blog.dao.pojo.SysUser;
import com.wym.blog.utils.UserThreadLocal;
import com.wym.blog.vo.Result;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("test")
public class TestController {

	@RequestMapping
	public Result test() {
		SysUser sysUser = UserThreadLocal.get();
		System.out.println(sysUser);
		return Result.success(null);
	}
}
