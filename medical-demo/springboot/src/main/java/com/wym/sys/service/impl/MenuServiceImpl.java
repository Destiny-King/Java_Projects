package com.wym.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wym.sys.entity.Menu;
import com.wym.sys.mapper.MenuMapper;
import com.wym.sys.service.IMenuService;
import org.springframework.stereotype.Service;

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
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements IMenuService {

	@Override
	public List<Menu> getAllMenu() {
		//一级菜单
		LambdaQueryWrapper<Menu> wrapper = new LambdaQueryWrapper<>();
		wrapper.eq(Menu::getParentId, 0);
		List<Menu> menuList = this.list(wrapper);
		//填充子菜单
		setMenuChildren(menuList);

		return menuList;
	}

	private void setMenuChildren(List<Menu> menuList) {
		if (menuList != null) {
			for (Menu menu : menuList) {
				LambdaQueryWrapper<Menu> subWrapper = new LambdaQueryWrapper<>();
				subWrapper.eq(Menu::getParentId, menu.getMenuId());
				List<Menu> subMenuList = this.list(subWrapper);
				menu.setChildren(subMenuList);

				//递归
				setMenuChildren(subMenuList);
			}
		}
	}


	private void setMenuChildrenByUserId(Integer userId, List<Menu> menuList) {
		if (menuList != null) {
			for (Menu menu : menuList) {
				List<Menu> subMenuList = this.baseMapper.getMenuListByUserId(userId, menu.getMenuId());
				menu.setChildren(subMenuList);
				//递归
				setMenuChildrenByUserId(userId, subMenuList);
			}
		}
	}

	@Override
	public List<Menu> getMenuListByUserId(Integer userId) {
		//一级菜单
		List<Menu> menuList = this.baseMapper.getMenuListByUserId(userId, 0);
		//填充子菜单
		setMenuChildrenByUserId(userId, menuList);

		return menuList;
	}
}
