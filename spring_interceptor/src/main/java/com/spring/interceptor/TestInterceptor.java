package com.spring.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class TestInterceptor  extends HandlerInterceptorAdapter{

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, 
							Object handler)
			throws Exception {
		Boolean result = false;
		
		result = "true".equals(request.getParameter("preHandle"));
		return result;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, 
						   Object handler, ModelAndView modelAndView) throws Exception {
		System.out.println("Model Attribute : " + modelAndView.getModel().get("message"));
		System.out.println("view name : " + modelAndView.getViewName());
		
		modelAndView.addObject("message","I'm postHandler");
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, 
								Object handler, Exception ex)
			throws Exception {
		
		if(ex!=null) System.out.println("Exception message : "+ ex.getMessage());
	}

	
}
