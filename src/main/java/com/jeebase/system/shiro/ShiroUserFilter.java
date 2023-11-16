package com.jeebase.system.shiro;

import java.io.IOException;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.shiro.web.filter.authc.UserFilter;

import com.alibaba.fastjson.JSON;
import com.jeebase.common.base.ResponseConstant;
import com.jeebase.common.base.Result;

public class ShiroUserFilter extends UserFilter {

	@Override
	protected void redirectToLogin(ServletRequest servletRequest, ServletResponse servletResponse) throws IOException {
		// 返回json
		servletResponse.setContentType("application/json; charset=utf-8");

		String json = JSON.toJSONString(new Result<String>().error(ResponseConstant.UNAUTHORIZED));

		servletResponse.getWriter().write(json);
	}

}
