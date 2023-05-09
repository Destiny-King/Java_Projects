package com.wym.user.service;

import com.wym.pojo.Address;
import com.wym.utils.R;

public interface AddressService {
	/**
	 * 根据用户id查询 地址数据
	 *
	 * @param userId
	 * @return
	 */
	R list(Integer userId);

	/**
	 * 插入地址数据
	 *
	 * @param address
	 * @return
	 */
	R save(Address address);

	/**
	 * 根据id删除地址数据
	 *
	 * @param id
	 * @return
	 */
	R remove(Integer id);
}
