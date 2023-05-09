package com.wym.blog.service;

import com.wym.blog.dao.pojo.SysUser;
import com.wym.blog.vo.Result;

public interface SysUserService {

	SysUser findUserById(Long id);

	SysUser findUser(String account, String password);

	/**
	 * 根据token查询用户信息
	 *
	 * @param token
	 * @return
	 */
	Result findUserByToken(String token);

	/**
	 * 根据账户查找用户
	 *
	 * @param account
	 * @return
	 */
	SysUser findUserByAccount(String account);

	/**
	 * 保存用户
	 *
	 * @param sysUser
	 */
	void save(SysUser sysUser);
}
