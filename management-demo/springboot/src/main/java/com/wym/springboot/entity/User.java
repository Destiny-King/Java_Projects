package com.wym.springboot.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

@Data
@TableName(value = "sys_user")
public class User {
	@TableId(value = "id")
	private Integer id;
	private String username;
	@JsonIgnore
	private String password;
	private String nickname;
	private String email;
	private String phone;
	private String address;
	@TableField(value = "avatar_url") //指定数据库的字段名称
	private String avatar;
}
