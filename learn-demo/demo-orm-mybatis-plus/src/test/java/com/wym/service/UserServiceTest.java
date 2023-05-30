package com.wym.service;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.util.IdUtil;
import cn.hutool.crypto.SecureUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wym.SpringBootMybatisPlusApplicationTests;
import com.wym.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.util.Lists;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
public class UserServiceTest extends SpringBootMybatisPlusApplicationTests {
	@Autowired
	private UserService userService;

	/**
	 * 新增
	 */
	@Test
	public void testSave() {
		String salt = IdUtil.fastSimpleUUID();
		User testSave3 = User.builder()
				.name("testSave3")
				.password(SecureUtil.md5("123456" + salt))
				.salt(salt).email("testSave3@wym.com")
				.phoneNumber("17353510196")
				.status(1)
				.lastLoginTime(new DateTime())
				.build();
		boolean save = userService.save(testSave3);
		Assert.assertTrue(save);
		log.debug("【测试id回显#testSave3.getId()】={}", testSave3.getId());
	}

	/**
	 * 批量新增
	 */
	public void testSaveList() {
		List<User> userList = Lists.newArrayList();
		for (int i = 4; i < 14; i++) {
			String salt = IdUtil.fastSimpleUUID();
			User user = User.builder()
					.name("testSave" + i)
					.password(SecureUtil.md5("123456" + salt))
					.salt(salt).email("testSave" + i + "@wym.com")
					.phoneNumber("17353510196" + i)
					.status(1)
					.lastLoginTime(new DateTime())
					.build();
			userList.add(user);
		}
		boolean batch = userService.saveBatch(userList);
		Assert.assertTrue(batch);
		List<Long> ids = userList.stream().map(User::getId).collect(Collectors.toList());
		log.debug("【userList#ids】={}", ids);
	}

	/**
	 * 删除
	 */
	@Test
	public void testDelete() {
		boolean remove = userService.removeById(1L);
		Assert.assertTrue(remove);
		User byId = userService.getById(1L);
		Assert.assertNull(byId);
	}

	/**
	 * 修改
	 */
	@Test
	public void testUpdate() {
		User user = userService.getById(1L);
		Assert.assertNotNull(user);
		user.setName("MybatisPlus修改名字");
		boolean b = userService.updateById(user);
		Assert.assertTrue(b);
		User update = userService.getById(1L);
		Assert.assertEquals("MybatisPlus修改名字", update.getName());
		log.debug("【update】= {}", update);
	}

	/**
	 * 查询单个
	 */
	@Test
	public void testQueryOne() {
		User user = userService.getById(1L);
		Assert.assertNotNull(user);
		log.debug("【user】= {}", user);
	}

	/**
	 * 查询全部
	 */
	@Test
	public void testQueryAll() {
		List<User> list = userService.list(new QueryWrapper<>());
		Assert.assertTrue(CollUtil.isNotEmpty(list));
		log.debug("【list】= {}", list);
	}

	/**
	 * 分页排序查询
	 */
	@Test
	public void testQueryByPageAndSort() {
		initData();
		int count = userService.count(new QueryWrapper<>());
		Page<User> userPage = new Page<>(1, 5);
		userPage.setDesc("id");
		IPage<User> page = userService.page(userPage, new QueryWrapper<>());
		Assert.assertEquals(5, page.getSize());
		Assert.assertEquals(count, page.getTotal());
		log.debug("【page.getRecords()】= {}", page.getRecords());
	}

	/**
	 * 自定义查询
	 */
	@Test
	public void testQueryByCondition() {
		initData();
		QueryWrapper<User> wrapper = new QueryWrapper<>();
		wrapper.like("name", "Save1").or().eq("phone_number", "17353510196").orderByDesc("id");
		int count = userService.count(wrapper);
		Page<User> userPage = new Page<>(1, 3);
		IPage<User> page = userService.page(userPage, wrapper);
		Assert.assertEquals(3, page.getSize());
		Assert.assertEquals(count, page.getTotal());
		log.debug("【page.getRecords()】= {}", page.getRecords());
	}


	/**
	 * 初始化数据
	 */
	private void initData() {
		testSave();
	}
}