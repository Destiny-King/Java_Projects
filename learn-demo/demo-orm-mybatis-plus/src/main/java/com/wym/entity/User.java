package com.wym.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

import static com.baomidou.mybatisplus.annotation.FieldFill.INSERT;
import static com.baomidou.mybatisplus.annotation.FieldFill.INSERT_UPDATE;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@TableName("orm_user")
public class User implements Serializable {
	private static final Long serialVersionUID = -1840831686851699943L;
	private Long id;
	private String name;
	private String password;
	private String salt;
	private String email;
	private String phoneNumber;
	private Integer status;
	@TableField(fill = INSERT)
	private Date createTime;
	private Date lastLoginTime;
	@TableField(fill = INSERT_UPDATE)
	private Date lastUpdateTime;
}
