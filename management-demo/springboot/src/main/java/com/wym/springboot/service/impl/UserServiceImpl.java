package com.wym.springboot.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wym.springboot.controller.dro.UserDto;
import com.wym.springboot.entity.User;
import com.wym.springboot.mapper.UserMapper;
import com.wym.springboot.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by romin on 2023/7/11.
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

	@Override
	public boolean saveUser(User user) {
		return saveOrUpdate(user);
	}

	@Override
	public boolean login(UserDto userDto) {
		QueryWrapper<User> wrapper = new QueryWrapper<>();
		wrapper.eq("username", userDto.getUsername());
		wrapper.eq("password", userDto.getPassword());
		List<User> list = list(wrapper);
		return list.size() != 0;
	}
}
