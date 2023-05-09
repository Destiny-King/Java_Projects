package com.wym.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 *
 * </p>
 *
 * @author wym
 * @since 2023-05-09
 */

@Data
public class Favorite implements Serializable {

	private static final long serialVersionUID = 1L;

	@TableId(value = "id", type = IdType.AUTO)
	private Integer id;

	private String wname;

	private String wurl;

	private Integer uid;

	private Integer type;

	private Integer count;

	private LocalDateTime ctime;

}
