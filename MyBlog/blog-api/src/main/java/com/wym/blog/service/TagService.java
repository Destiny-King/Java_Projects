package com.wym.blog.service;

import com.wym.blog.vo.Result;
import com.wym.blog.vo.TagVo;

import java.util.List;

public interface TagService {

	List<TagVo> findTagsByArticleId(Long articleId);

	Result hots(int limit);
}
