package com.wym.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.wym.constants.UserConstants;
import com.wym.param.UserCheckParam;
import com.wym.param.UserLoginParam;
import com.wym.pojo.User;
import com.wym.user.mapper.UserMapper;
import com.wym.user.service.UserService;
import com.wym.utils.MD5Util;
import com.wym.utils.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserMapper userMapper;

	@Override
	public R check(UserCheckParam userCheckParam) {
		//参数封装
		QueryWrapper<User> queryWrapper = new QueryWrapper<>();
		queryWrapper.eq("user_name", userCheckParam.getUserName());
		//数据库查询
		Long total = userMapper.selectCount(queryWrapper);
		//查询结果处理
		if (total == 0) {
			log.info("UserServiceImpl.check业务结束，结果:{}", "账号可以使用!");
			return R.ok("账号不存在，可用使用！");
		}
		log.info("UserServiceImpl.check业务结束，结果:{}", "账号不可使用!");
		return R.fail("账号已经存在，不可注册！");
	}

	@Override
	public R register(User user) {
		//检查账号是否存在
		QueryWrapper<User> queryWrapper = new QueryWrapper<>();
		queryWrapper.eq("user_name", user.getUserName());
		//数据库查询
		Long total = userMapper.selectCount(queryWrapper);

		if (total > 0) {
			log.info("UserServiceImpl.register业务结束，结果:{}", "账号存在，注册失败!");
			return R.fail("账号已经存在，不可注册！");
		}
		//密码加密处理，加盐
		String newPwd = MD5Util.encode(user.getPassword() + UserConstants.USER_SALT);
		user.setPassword(newPwd);

		//插入数据库数据
		int rows = userMapper.insert(user);

		//返回封装结果
		if (rows == 0) {
			log.info("UserServiceImpl.register业务结束，结果:{}", "数据插入失败，注册失败!");
			return R.fail("注册失败，请稍后再试！");
		}

		return R.ok("注册成功！");
	}

	@Override
	public R login(UserLoginParam userLoginParam) {
		//加密处理
		String newPwd = MD5Util.encode(userLoginParam.getPassword() + UserConstants.USER_SALT);
		//数据库查询
		QueryWrapper<User> queryWrapper = new QueryWrapper<>();
		queryWrapper.eq("user_name", userLoginParam.getUserName());
		queryWrapper.eq("password", newPwd);

		User user = userMapper.selectOne(queryWrapper);
		//结果处理
		if (user == null) {
			log.info("UserServiceImpl.login业务结束，结果:{}", "账号和密码错误!");
			return R.fail("账号或密码错误！");
		}
		log.info("UserServiceImpl.login业务结束，结果:{}", "登录成功!");
		user.setPassword(null);

		return R.ok("登录成功", user);
	}
}
