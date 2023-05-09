package com.wym.blog.service;

import com.wym.blog.dao.pojo.SysUser;
import com.wym.blog.vo.Result;
import com.wym.blog.vo.params.LoginParam;

public interface LoginService {

	/**
	 * 登录功能
	 *
	 * @param loginParam
	 * @return
	 */
	Result login(LoginParam loginParam);

	SysUser checkToken(String token);

	/**
	 * 退出登录
	 *
	 * @param token
	 * @return
	 */
	Result logout(String token);

	/**
	 * 注册功能
	 *
	 * @param loginParam
	 * @return
	 */
	Result register(LoginParam loginParam);
}
