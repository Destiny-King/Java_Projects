package com.wym.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wym.entity.Login;

import java.util.Map;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author wym
 * @since 2023-05-09
 */
public interface ILoginService extends IService<Login> {

	Map<String, Object> login(Login login);
}
