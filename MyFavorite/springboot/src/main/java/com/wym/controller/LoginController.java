package com.wym.controller;

import com.wym.entity.Login;
import com.wym.service.ILoginService;
import com.wym.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author wym
 * @since 2023-05-09
 */
@RestController
@RequestMapping("/login")
public class LoginController {
	@Autowired
	private ILoginService loginService;

	@GetMapping("/all")
	public Result<List<Login>> getAllLogin() {
		List<Login> loginList = loginService.list();
		return Result.success(loginList, "查询成功");
	}

	@PostMapping("/login")
	public Result<Map<String, Object>> login(@RequestBody Login login) {
		Map<String, Object> data = loginService.login(login);
		if (data != null) {
			return Result.success(data);
		}
		return Result.fail(20002, "用户名或密码错误");
	}

}
