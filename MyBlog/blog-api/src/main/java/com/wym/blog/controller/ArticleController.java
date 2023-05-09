package com.wym.blog.controller;

import com.wym.blog.service.ArticleService;
import com.wym.blog.vo.Result;
import com.wym.blog.vo.params.PageParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("articles")
public class ArticleController {

	@Autowired
	private ArticleService articleService;

	/**
	 * 首页 文章列表
	 *
	 * @param pageParams
	 */

	@PostMapping
	public Result listArticle(@RequestBody PageParams pageParams) {

		return articleService.listArticle(pageParams);
	}

	/**
	 * 首页 最热文章
	 *
	 * @return
	 */

	@PostMapping("hot")
	public Result hotArticle() {
		int limit = 5;
		return articleService.hotArticle(limit);
	}

	/**
	 * 首页 最新文章
	 *
	 * @return
	 */

	@PostMapping("new")
	public Result newArticles() {
		int limit = 5;
		return articleService.newArticles(limit);
	}

	/**
	 * 首页 文章归档
	 *
	 * @return
	 */

	@PostMapping("listArchives")
	public Result listArchives() {
		return articleService.listArchives();
	}

	/**
	 * 查看文章详情
	 *
	 * @param articleId
	 * @return
	 */
	@PostMapping("view/{id}")
	public Result findArticleById(@PathVariable("id") Long articleId) {
		return articleService.findArticleById(articleId);
	}
}