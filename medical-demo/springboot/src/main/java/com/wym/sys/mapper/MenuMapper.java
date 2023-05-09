package com.wym.sys.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wym.sys.entity.Menu;
import org.apache.ibatis.annotations.Param;

import java.util.List;


/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author wym
 * @since 2023-02-14
 */
public interface MenuMapper extends BaseMapper<Menu> {
	public List<Menu> getMenuListByUserId(@Param("userId") Integer userId, @Param("pid") Integer pid);
}
