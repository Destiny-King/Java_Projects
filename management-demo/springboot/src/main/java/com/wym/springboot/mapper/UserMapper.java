package com.wym.springboot.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wym.springboot.entity.User;

//@Mapper
public interface UserMapper extends BaseMapper<User> {

//	@Select("SELECT * FROM sys_user")
//	List<User> findAll();
//
//	@Insert("INSERT INTO sys_user (username,`password`,nickname,email,phone,address) VALUES " +
//			"(#{username},#{password},#{nickname},#{email},#{phone},#{address})")
//	int insert(User user);
//
//	int update(User user);
//
//	@Delete("delete from sys_user where id = #{id}")
//	Integer deleteById(@Param("id") Integer id);
//
//	@Select("select * from sys_user where username like concat('%',#{username},'%') limit #{pageNum}, #{pageSize}")
//	List<User> selectPage(Integer pageNum, Integer pageSize, String username);
//
//	@Select("select count(*) from sys_user")
//	Integer selectTotal(String username);
}
