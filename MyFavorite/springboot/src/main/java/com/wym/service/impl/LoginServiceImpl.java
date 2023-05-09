package com.wym.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wym.entity.Login;
import com.wym.mapper.LoginMapper;
import com.wym.service.ILoginService;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author wym
 * @since 2023-05-09
 */
@Service
public class LoginServiceImpl extends ServiceImpl<LoginMapper, Login> implements ILoginService {

	@Override
	public Map<String, Object> login(Login login) {
		//查询
		LambdaQueryWrapper<Login> wrapper = new LambdaQueryWrapper<>();
		wrapper.eq(Login::getUsername, login.getUsername());
		wrapper.eq(Login::getPassword, login.getPassword());
		Login loginUser = this.baseMapper.selectOne(wrapper);
		//结果不为空，生成token
		if (loginUser != null) {
			String key = "user:" + UUID.randomUUID();

			//存入redis

			//返回数据
			HashMap<String, Object> data = new HashMap<>();
			data.put("token", key);
			return data;
		}

		return null;
	}
}
