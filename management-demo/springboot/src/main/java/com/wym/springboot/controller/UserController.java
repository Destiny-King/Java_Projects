package com.wym.springboot.controller;

import cn.hutool.core.util.StrUtil;
import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wym.springboot.controller.dro.UserDto;
import com.wym.springboot.entity.User;
import com.wym.springboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.net.URLEncoder;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;

	@PostMapping("/login")
	public boolean login(@RequestBody UserDto userDto) {
		String username = userDto.getUsername();
		String password = userDto.getPassword();
		if (StrUtil.isBlank(username) || StrUtil.isBlank(password)) {
			return false;
		}
		return userService.login(userDto);
	}

	//新增和修改
	@PostMapping
	public boolean save(@RequestBody User user) {
		//新增或更新
		return userService.saveUser(user);
	}

	//查询所有数据
	@GetMapping
	public List<User> fingAll() {

		return userService.list();
	}

	//删除
	@DeleteMapping("/{id}")
	public boolean delete(@PathVariable Integer id) {
		return userService.removeById(id);
	}

	//批量删除
	@PostMapping("/del/batch")
	public boolean deleteBatch(@RequestBody List<Integer> ids) {
		return userService.removeByIds(ids);
	}

	//分页查询
//	@GetMapping("/page")
//	public Map<String, Object> fingPage(@RequestParam Integer pageNum,
//	                                    @RequestParam Integer pageSize,
//	                                    @RequestParam String username) {
//		pageNum = (pageNum - 1) * pageSize;
//		List<User> data = userMapper.selectPage(pageNum, pageSize, username);
//		Integer total = userMapper.selectTotal(username);
//		Map<String, Object> res = new HashMap<>();
//		res.put("data", data);
//		res.put("total", total);
//		return res;
//	}

	//	分页查询: mybatis-plus
	@GetMapping("/page")
	public IPage<User> fingPage(@RequestParam Integer pageNum,
	                            @RequestParam Integer pageSize,
	                            @RequestParam(defaultValue = "") String username,
	                            @RequestParam(defaultValue = "") String email,
	                            @RequestParam(defaultValue = "") String address) {
		IPage<User> page = new Page<>(pageNum, pageSize);
		QueryWrapper<User> queryWrapper = new QueryWrapper<>();
		if (!"".equals(username)) {
			queryWrapper.like("username", username);
		}
		if (!"".equals(email)) {
			queryWrapper.like("email", email);
		}
		if (!"".equals(address)) {
			queryWrapper.like("address", address);
		}
		queryWrapper.orderByDesc("id");
		return userService.page(page, queryWrapper);
	}

	/**
	 * 导出接口
	 */
	@GetMapping("/export")
	public void export(HttpServletResponse response) throws Exception {
		List<User> list = userService.list();
		//在内存操作，写出到浏览器
		ExcelWriter writer = ExcelUtil.getWriter();
		writer.addHeaderAlias("username", "用户名");
		writer.addHeaderAlias("password", "密码");
		writer.addHeaderAlias("nickname", "昵称");
		writer.addHeaderAlias("email", "邮箱");
		writer.addHeaderAlias("phone", "电话");
		writer.addHeaderAlias("address", "地址");
		writer.addHeaderAlias("createTime", "创建时间");
		writer.addHeaderAlias("avatarUrl", "头像");

		//一次性写出list内的对象到excel
		writer.write(list, true);

		response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;charset=utf-8");
		String fileName = URLEncoder.encode("用户信息", "UTF-8");
		response.setHeader("Content-Disposition", "attachment;filename=" + fileName + ".xlsx");

		ServletOutputStream out = response.getOutputStream();
		writer.flush(out, true);
		out.close();
		writer.close();
	}
}
