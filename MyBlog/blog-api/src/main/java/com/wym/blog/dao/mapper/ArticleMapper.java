package com.wym.blog.dao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wym.blog.dao.dos.Archives;
import com.wym.blog.dao.pojo.Article;

import java.util.List;

public interface ArticleMapper extends BaseMapper<Article> {
	List<Archives> listArchives();
}
