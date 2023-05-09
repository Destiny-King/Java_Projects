package com.wym.sys.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wym.sys.entity.RoleMenu;

import java.util.List;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author wym
 * @since 2023-02-14
 */
public interface RoleMenuMapper extends BaseMapper<RoleMenu> {

	public List<Integer> getMenuIdListByRoleId(Integer roleId);
}
