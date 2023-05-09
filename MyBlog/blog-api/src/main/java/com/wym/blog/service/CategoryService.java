package com.wym.blog.service;

import com.wym.blog.vo.CategoryVo;

public interface CategoryService {
	CategoryVo findCategoryById(Long categoryId);
}
