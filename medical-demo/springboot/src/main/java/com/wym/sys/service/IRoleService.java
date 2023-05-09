package com.wym.sys.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wym.sys.entity.Role;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author wym
 * @since 2023-02-14
 */
public interface IRoleService extends IService<Role> {

	void addRole(Role role);

	Role getRoleById(Integer id);

	void updateRole(Role role);

	void deleteRoleById(Integer id);
}
