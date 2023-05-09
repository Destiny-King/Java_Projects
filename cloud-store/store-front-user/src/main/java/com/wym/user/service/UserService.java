package com.wym.user.service;

import com.wym.param.UserCheckParam;
import com.wym.param.UserLoginParam;
import com.wym.pojo.User;
import com.wym.utils.R;

public interface UserService {

	/**
	 * 检查账号是否可用
	 *
	 * @param userCheckParam
	 * @return
	 */
	R check(UserCheckParam userCheckParam);

	/**
	 * 注册
	 *
	 * @param user
	 * @return
	 */
	R register(User user);

	/**
	 * 登录
	 *
	 * @param userLoginParam
	 * @return
	 */
	R login(UserLoginParam userLoginParam);
}
