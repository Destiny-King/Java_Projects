package com.wym.blog.handler;

import com.alibaba.fastjson.JSON;
import com.wym.blog.dao.pojo.SysUser;
import com.wym.blog.service.LoginService;
import com.wym.blog.utils.UserThreadLocal;
import com.wym.blog.vo.ErrorCode;
import com.wym.blog.vo.Result;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
@Slf4j
public class LoginInterceptor implements HandlerInterceptor {

	@Autowired
	private LoginService loginService;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		/**
		 * 1、判断请求的接口路径是否为HandlerMethod
		 * 2、判断token是否为空
		 * 3、登录验证loginService checkToken
		 * 4、如果认证成功，放行
		 */
		if (!(handler instanceof HandlerMethod)) {
			return true;
		}
		String token = request.getHeader("Authorization");

		log.info("==============request start==============");
		String requestURI = request.getRequestURI();
		log.info("request uri:{}", requestURI);
		log.info("request method:{}", request.getMethod());
		log.info("token:{}", token);
		log.info("==============request end==============");

		if (StringUtils.isBlank(token)) {
			Result result = Result.fail(ErrorCode.NO_LOGIN.getCode(), "未登录");
			response.setContentType("application/json;charset=utf-8");
			response.getWriter().print(JSON.toJSONString(result));
			return false;
		}
		SysUser sysUser = loginService.checkToken(token);
		if (sysUser == null) {
			Result result = Result.fail(ErrorCode.NO_LOGIN.getCode(), "未登录");
			response.setContentType("application/json;charset=utf-8");
			response.getWriter().print(JSON.toJSONString(result));
			return false;
		}
		UserThreadLocal.put(sysUser);
		return true;
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
		UserThreadLocal.remove();
	}
}
