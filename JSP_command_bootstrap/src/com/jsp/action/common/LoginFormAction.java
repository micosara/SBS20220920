package com.jsp.action.common;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jsp.action.Action;

public class LoginFormAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String url="/common/loginForm";
		
		String error=request.getParameter("error");
		if(error!=null && error.equals("-1")) {
			response.setStatus(302);
		}
		
		String retUrl = request.getParameter("retUrl");
		
		request.setAttribute("retUrl", retUrl);		
		
		return url;
	}

}
