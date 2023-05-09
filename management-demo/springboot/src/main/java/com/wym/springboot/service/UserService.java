package com.wym.springboot.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wym.springboot.entity.User;
import com.wym.springboot.mapper.UserMapper;
import org.springframework.stereotype.Service;

@Service
public class UserService extends ServiceImpl<UserMapper, User> {

//	@Autowired
//	private UserMapper userMapper;
//
//	public int save(User user) {
//		if (user.getId() == null) { //user没有id，则表示是新增
//			return userMapper.insert(user);
//		} else { //否则为更新
//			return userMapper.update(user);
//		}
//	}

	public boolean saveUser(User user) {
		return saveOrUpdate(user);
	}
}
