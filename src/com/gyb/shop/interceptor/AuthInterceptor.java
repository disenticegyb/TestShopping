package com.gyb.shop.interceptor;

import java.util.Arrays;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.StrutsStatics;
import org.springframework.beans.factory.annotation.Autowired;

import com.gyb.shop.pojo.User;
import com.gyb.shop.service.OrderItemService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
/**
 * 配置都无车拦截器，对于没有登陆访问是不能被访问到的，返回登陆界面
 * @author disentice
 *
 */
public class AuthInterceptor extends AbstractInterceptor{

	@Autowired
	OrderItemService orderItemService;

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		String[] noNeedAuthPage = new String[] { "home", "checkLogin", "register", "loginAjax", "login", "product",
				"category", "search" };

		ActionContext ctx = invocation.getInvocationContext();
		HttpServletRequest request = (HttpServletRequest) ctx.get(StrutsStatics.HTTP_REQUEST);
		HttpServletResponse response = (HttpServletResponse) ctx.get(StrutsStatics.HTTP_RESPONSE);
		ServletContext servletContext = (ServletContext) ctx.get(StrutsStatics.SERVLET_CONTEXT);
		String contextPath = servletContext.getContextPath();
		String uri = request.getRequestURI();
		uri = StringUtils.remove(uri, contextPath);
		if (uri.startsWith("/fore")) {
			String method = StringUtils.substringAfterLast(uri, "/fore");
			if (!Arrays.asList(noNeedAuthPage).contains(method)) {
				User user = (User) ctx.getSession().get("user");
				if (null == user) {
					response.sendRedirect("login.jsp");
					return null;
				}
			}
		}
		return invocation.invoke();
	}

}
