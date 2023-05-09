package com.wym.user.controller;

import com.wym.param.UserCheckParam;
import com.wym.param.UserLoginParam;
import com.wym.pojo.User;
import com.wym.user.service.UserService;
import com.wym.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;

	/**
	 * 检查账号是否可用
	 *
	 * @param userCheckParam
	 * @param result
	 * @return
	 */
	@PostMapping("/check")
	public R check(@RequestBody @Validated UserCheckParam userCheckParam, BindingResult result) {
		//检查是否符合校验规则
		boolean b = result.hasErrors();

		if (b) {
			return R.fail("账号为空，不可使用！");
		}
		return userService.check(userCheckParam);
	}

	@PostMapping("/register")
	public R register(@RequestBody @Validated User user, BindingResult result) {
		if (result.hasErrors()) {
			return R.fail("参数异常，不可注册！");
		}
		return userService.register(user);
	}

	@PostMapping("/login")
	public R login(@RequestBody @Validated UserLoginParam userLoginParam, BindingResult result) {
		if (result.hasErrors()) {
			return R.fail("参数异常，不可登录！");
		}
		return userService.login(userLoginParam);
	}
}
