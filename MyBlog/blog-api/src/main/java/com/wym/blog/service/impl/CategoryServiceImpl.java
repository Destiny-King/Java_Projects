package com.wym.blog.service.impl;

import com.wym.blog.dao.mapper.CategoryMapper;
import com.wym.blog.dao.pojo.Category;
import com.wym.blog.service.CategoryService;
import com.wym.blog.vo.CategoryVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	private CategoryMapper categoryMapper;

	@Override
	public CategoryVo findCategoryById(Long categoryId) {
		Category category = categoryMapper.selectById(categoryId);
		CategoryVo categoryVo = new CategoryVo();
		BeanUtils.copyProperties(category, categoryVo);
		return categoryVo;
	}
}
