package com.wym.blog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.wym.blog.dao.mapper.SysUserMapper;
import com.wym.blog.dao.pojo.SysUser;
import com.wym.blog.service.LoginService;
import com.wym.blog.service.SysUserService;
import com.wym.blog.vo.ErrorCode;
import com.wym.blog.vo.LoginUserVo;
import com.wym.blog.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class SysUserServiceImpl implements SysUserService {

	@Autowired
	private SysUserMapper sysUserMapper;

	@Autowired
	private RedisTemplate<String, String> redisTemplate;

	@Autowired
	private LoginService loginService;

	@Override
	public SysUser findUserById(Long id) {
		SysUser sysUser = sysUserMapper.selectById(id);
		if (sysUser == null) {
			sysUser = new SysUser();
			sysUser.setNickname("wym");
		}
		return sysUser;
	}

	@Override
	public SysUser findUser(String account, String password) {
		LambdaQueryWrapper<SysUser> queryWrapper = new LambdaQueryWrapper<>();
		queryWrapper.eq(SysUser::getAccount, account);
		queryWrapper.eq(SysUser::getPassword, password);
		queryWrapper.select(SysUser::getAccount, SysUser::getId, SysUser::getAvatar, SysUser::getNickname);
		queryWrapper.last("limit 1");

		return sysUserMapper.selectOne(queryWrapper);
	}

	@Override
	public Result findUserByToken(String token) {
		SysUser sysUser = loginService.checkToken(token);
		if (sysUser == null) {
			Result.fail(ErrorCode.TOKEN_ERROR.getCode(), ErrorCode.TOKEN_ERROR.getMsg());
		}
		LoginUserVo loginUserVo = new LoginUserVo();
		loginUserVo.setId(sysUser.getId());
		loginUserVo.setNickname(sysUser.getNickname());
		loginUserVo.setAvatar(sysUser.getAvatar());
		loginUserVo.setAccount(sysUser.getAccount());
		return Result.success(loginUserVo);
	}

	@Override
	public SysUser findUserByAccount(String account) {
		LambdaQueryWrapper<SysUser> queryWrapper = new LambdaQueryWrapper<>();
		queryWrapper.eq(SysUser::getAccount, account);
		queryWrapper.last("limit 1");
		return this.sysUserMapper.selectOne(queryWrapper);
	}

	@Override
	public void save(SysUser sysUser) {
		this.sysUserMapper.insert(sysUser);
	}
}
