package com.wym.carousel.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.wym.carousel.mapper.CarouselMapper;
import com.wym.carousel.service.CarouselService;
import com.wym.pojo.Carousel;
import com.wym.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CarouselServiceImpl implements CarouselService {
	@Autowired
	private CarouselMapper carouselMapper;

	@Override
	public R list() {
		QueryWrapper<Carousel> carouselQueryWrapper = new QueryWrapper<>();
		carouselQueryWrapper.orderByDesc("priority");

		List<Carousel> list = carouselMapper.selectList(carouselQueryWrapper);
		List<Carousel> collect = list.stream().limit(6).collect(Collectors.toList());

		return R.ok(collect);
	}
}
