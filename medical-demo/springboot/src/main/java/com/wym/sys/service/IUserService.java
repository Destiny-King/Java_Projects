package com.wym.sys.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wym.sys.entity.User;

import java.util.Map;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author wym
 * @since 2023-02-14
 */
public interface IUserService extends IService<User> {

	Map<String, Object> login(User user);

	Map<String, Object> getUserInfo(String token);

	void logout(String token);

	void addUser(User user);

	User getUserById(Integer id);

	void updateUser(User user);

	void deleteUserById(Integer id);
}
