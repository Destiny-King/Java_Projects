package com.wym.blog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wym.blog.dao.dos.Archives;
import com.wym.blog.dao.mapper.ArticleBodyMapper;
import com.wym.blog.dao.mapper.ArticleMapper;
import com.wym.blog.dao.pojo.Article;
import com.wym.blog.dao.pojo.ArticleBody;
import com.wym.blog.service.*;
import com.wym.blog.vo.ArticleBodyVo;
import com.wym.blog.vo.ArticleVo;
import com.wym.blog.vo.Result;
import com.wym.blog.vo.params.PageParams;
import org.joda.time.DateTime;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ArticleServiceImpl implements ArticleService {

	@Autowired
	private ArticleMapper articleMapper;

	@Autowired
	private TagService tagService;

	@Autowired
	private SysUserService sysUserService;

	@Override
	public Result listArticle(PageParams pageParams) {
		/**
		 * 分页查询 article数据库表
		 */
		Page<Article> page = new Page<>(pageParams.getPage(), pageParams.getPageSize());
		LambdaQueryWrapper<Article> queryWrapper = new LambdaQueryWrapper<>();
		//是否置顶进行排序
		queryWrapper.orderByDesc(Article::getWeight, Article::getCreateDate);
		Page<Article> articlePage = articleMapper.selectPage(page, queryWrapper);
		List<Article> records = articlePage.getRecords();

		List<ArticleVo> articleVoList = copyList(records, true, true);
		return Result.success(articleVoList);
	}

	@Override
	public Result hotArticle(int limit) {
		LambdaQueryWrapper<Article> queryWrapper = new LambdaQueryWrapper<>();
		queryWrapper.orderByDesc(Article::getTitle);
		queryWrapper.select(Article::getId, Article::getTitle);
		queryWrapper.last("limit " + limit);
		//select id.title from article order by view_counts desc limit 5
		List<Article> articles = articleMapper.selectList(queryWrapper);
		return Result.success(copyList(articles, false, false));
	}

	@Override
	public Result newArticles(int limit) {
		LambdaQueryWrapper<Article> queryWrapper = new LambdaQueryWrapper<>();
		queryWrapper.orderByDesc(Article::getCreateDate);
		queryWrapper.select(Article::getId, Article::getTitle);
		queryWrapper.last("limit " + limit);
		//select id.title from article order by create_date desc limit 5
		List<Article> articles = articleMapper.selectList(queryWrapper);
		return Result.success(copyList(articles, false, false));
	}

	@Override
	public Result listArchives() {
		List<Archives> archivesList = articleMapper.listArchives();
		return Result.success(archivesList);
	}

	@Autowired
	private ThreadService threadService;

	@Override
	public Result findArticleById(Long articleId) {
		/**
		 * 1、根据id查询
		 * 2、茛聚bodyId和categoryid做关联查询
		 */
		Article article = this.articleMapper.selectById(articleId);
		ArticleVo articleVo = copy(article, true, true, true, true);
		threadService.updateArticleViewCount(articleMapper, article);
		return Result.success(articleVo);
	}

	private List<ArticleVo> copyList(List<Article> records, boolean isTag, boolean isAuthor) {
		List<ArticleVo> articleVoList = new ArrayList<>();
		for (Article record : records) {
			articleVoList.add(copy(record, isTag, isAuthor, false, false));
		}
		return articleVoList;
	}

	private List<ArticleVo> copyList(List<Article> records, boolean isTag, boolean isAuthor, boolean isBody, boolean isCategory) {
		List<ArticleVo> articleVoList = new ArrayList<>();
		for (Article record : records) {
			articleVoList.add(copy(record, isTag, isAuthor, isBody, isCategory));
		}
		return articleVoList;
	}


	@Autowired
	private CategoryService categoryService;

	private ArticleVo copy(Article article, boolean isTag, boolean isAuthor, boolean isBody, boolean isCategory) {
		ArticleVo articleVo = new ArticleVo();
		BeanUtils.copyProperties(article, articleVo);
		articleVo.setCreateDate(new DateTime(article.getCreateDate()).toString("yyyy-MM-dd HH:mm"));
		if (isTag) {
			Long articleId = article.getId();
			articleVo.setTags(tagService.findTagsByArticleId(articleId));
		}
		if (isAuthor) {
			Long authorId = article.getAuthorId();
			articleVo.setAuthor(sysUserService.findUserById(authorId).getNickname());
		}
		if (isBody) {
			Long bodyId = article.getBodyId();
			articleVo.setBody(findArticleBodyById(bodyId));
		}
		if (isCategory) {
			Long categoryId = article.getCategoryId();
			articleVo.setCategory(categoryService.findCategoryById(categoryId));
		}
		return articleVo;
	}

	@Autowired
	private ArticleBodyMapper articleBodyMapper;

	private ArticleBodyVo findArticleBodyById(Long bodyId) {
		ArticleBody articleBody = articleBodyMapper.selectById(bodyId);
		ArticleBodyVo articleBodyVo = new ArticleBodyVo();
		articleBodyVo.setContent(articleBody.getContent());
		return articleBodyVo;
	}
}
