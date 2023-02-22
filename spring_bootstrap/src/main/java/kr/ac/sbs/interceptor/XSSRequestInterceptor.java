package kr.ac.sbs.interceptor;

import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.josephoconnell.html.HTMLInputFilter;

public class XSSRequestInterceptor extends HandlerInterceptorAdapter{

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		Enumeration<String> crossParamNames = request.getParameterNames();
		
		while(crossParamNames.hasMoreElements()) {
			
			String paramName = crossParamNames.nextElement();
			String paramValue = request.getParameter(paramName);
			
			request.setAttribute("XSS"+paramName, HTMLInputFilter.htmlSpecialChars(paramValue));				
			
		}
		
		return true;
	}

}
