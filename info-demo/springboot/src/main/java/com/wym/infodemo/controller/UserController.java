package com.wym.infodemo.controller;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wym.infodemo.common.Result;
import com.wym.infodemo.entity.User;
import com.wym.infodemo.mapper.UserMapper;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/user")
public class UserController {

	@Resource
	UserMapper userMapper;

	//登录
	@PostMapping("/login")
	public Result<?> login(@RequestBody User user) {
		User res = userMapper.selectOne(Wrappers.<User>lambdaQuery().eq(User::getUsername, user.getUsername())
				.eq(User::getPassword, user.getPassword()));
		if (res == null) {
			return Result.error("-1", "用户名或密码错误");
		}
		return Result.success(res);
	}

	//注册
	@PostMapping("/register")
	public Result<?> register(@RequestBody User user) {
		User res = userMapper.selectOne(Wrappers.<User>lambdaQuery().eq(User::getUsername, user.getUsername()));
		if (res != null) {
			return Result.error("-1", "用户名重复");
		}
		if (user.getPassword() == null) {
			user.setPassword("123");
		}
		userMapper.insert(user);
		return Result.success();
	}

	//新增
	@PostMapping
	public Result<?> save(@RequestBody User user) {
		if (user.getPassword() == null) {
			user.setPassword("123");
		}
		userMapper.insert(user);
		return Result.success();
	}

	//更新
	@PutMapping
	public Result<?> update(@RequestBody User user) {
		userMapper.updateById(user);
		return Result.success();
	}

	//删除
	@DeleteMapping("/{id}")
	public Result<?> delete(@PathVariable Long id) {
		userMapper.deleteById(id);
		return Result.success();
	}

	//查询
	@GetMapping
	public Result<?> findPage(@RequestParam(defaultValue = "1") Integer pageNum,
	                          @RequestParam(defaultValue = "10") Integer pageSize,
	                          @RequestParam(defaultValue = "") String search) {
		LambdaQueryWrapper<User> wrapper = Wrappers.<User>lambdaQuery();
		if (StrUtil.isNotBlank(search)) {
			wrapper.like(User::getNickName, search);
		}
		Page<User> userPage = userMapper.selectPage(new Page<>(pageNum, pageSize), wrapper);
		return Result.success(userPage);
	}
}
