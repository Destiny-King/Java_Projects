package com.wym.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.wym.pojo.Address;
import com.wym.user.mapper.AddressMapper;
import com.wym.user.service.AddressService;
import com.wym.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressServiceImpl implements AddressService {

	@Autowired
	private AddressMapper addressMapper;

	@Override
	public R list(Integer userId) {
		QueryWrapper<Address> queryWrapper = new QueryWrapper<>();
		queryWrapper.eq("user_id", userId);
		List<Address> addressList = addressMapper.selectList(queryWrapper);

		return R.ok("查询成功", addressList);
	}

	@Override
	public R save(Address address) {
		int rows = addressMapper.insert(address);
		if (rows == 0) {
			R.fail("插入地址失败");
		}
		//复用查询业务
		return list(address.getUserId());
	}

	@Override
	public R remove(Integer id) {
		int rows = addressMapper.deleteById(id);
		if (rows == 0) {
			R.fail("删除地址失败");
		}

		return R.ok("删除地址成功");
	}
}
