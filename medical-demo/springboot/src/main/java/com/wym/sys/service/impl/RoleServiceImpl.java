package com.wym.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wym.sys.entity.Role;
import com.wym.sys.entity.RoleMenu;
import com.wym.sys.mapper.RoleMapper;
import com.wym.sys.mapper.RoleMenuMapper;
import com.wym.sys.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author wym
 * @since 2023-02-14
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements IRoleService {
	@Autowired
	private RoleMenuMapper roleMenuMapper;

	@Override
	@Transactional
	public void addRole(Role role) {
		//写入角色表
		this.baseMapper.insert(role);
		//写入角色菜单关系表
		if (null != role.getMenuIdList()) {
			for (Integer menuId : role.getMenuIdList()) {
				roleMenuMapper.insert(new RoleMenu(null, role.getRoleId(), menuId));
			}
		}
	}

	@Override
	public Role getRoleById(Integer id) {
		Role role = this.baseMapper.selectById(id);
		List<Integer> menuIdList = roleMenuMapper.getMenuIdListByRoleId(id);
		role.setMenuIdList(menuIdList);
		return role;
	}

	@Override
	@Transactional
	public void updateRole(Role role) {
		//修改角色表
		this.baseMapper.updateById(role);
		//删除原有权限
		LambdaQueryWrapper<RoleMenu> wrapper = new LambdaQueryWrapper<>();
		wrapper.eq(RoleMenu::getRoleId, role.getRoleId());
		roleMenuMapper.delete(wrapper);
		//新增权限
		if (null != role.getMenuIdList()) {
			for (Integer menuId : role.getMenuIdList()) {
				roleMenuMapper.insert(new RoleMenu(null, role.getRoleId(), menuId));
			}
		}
	}

	@Override
	@Transactional
	public void deleteRoleById(Integer id) {
		this.baseMapper.deleteById(id);
		//删除原有权限
		LambdaQueryWrapper<RoleMenu> wrapper = new LambdaQueryWrapper<>();
		wrapper.eq(RoleMenu::getRoleId, id);
		roleMenuMapper.delete(wrapper);
	}
}
