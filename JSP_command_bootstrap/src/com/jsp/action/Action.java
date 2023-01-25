package com.jsp.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface Action {
	
	public String execute(HttpServletRequest request, 
						  HttpServletResponse response) throws Exception;
}
