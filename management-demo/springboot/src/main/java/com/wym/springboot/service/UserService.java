package com.wym.springboot.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wym.springboot.controller.dro.UserDto;
import com.wym.springboot.entity.User;
import org.springframework.stereotype.Service;

@Service
public interface UserService extends IService<User> {

//	@Autowired
//	private UserMapper userMapper;
//
//	public int save(User user) {
//		if (user.getId() == null) { //user没有id，则表示是新增fatkun
//			return userMapper.insert(user);
//		} else { //否则为更新
//			return userMapper.update(user);
//		}
//	}

	boolean saveUser(User user);

	boolean login(UserDto userDto);
}
