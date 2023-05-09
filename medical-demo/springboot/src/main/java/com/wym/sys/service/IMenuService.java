package com.wym.sys.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wym.sys.entity.Menu;

import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author wym
 * @since 2023-02-14
 */
public interface IMenuService extends IService<Menu> {

	List<Menu> getAllMenu();

	List<Menu> getMenuListByUserId(Integer userId);
}
