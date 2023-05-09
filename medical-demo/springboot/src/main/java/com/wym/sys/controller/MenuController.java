package com.wym.sys.controller;

import com.wym.common.vo.Result;
import com.wym.sys.entity.Menu;
import com.wym.sys.service.IMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author wym
 * @since 2023-02-14
 */
@RestController
@RequestMapping("/menu")
public class MenuController {
	@Autowired
	private IMenuService menuService;

	@GetMapping
	public Result<List<Menu>> getAllMenu() {
		List<Menu> menuList = menuService.getAllMenu();
		return Result.success(menuList);
	}
}
