package com.wym.sys.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wym.sys.entity.User;

import java.util.List;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author wym
 * @since 2023-02-14
 */
public interface UserMapper extends BaseMapper<User> {
	public List<String> getRoleNameByUserId(Integer userId);
}
