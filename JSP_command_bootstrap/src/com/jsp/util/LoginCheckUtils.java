package com.jsp.util;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginCheckUtils {
	
	public static boolean proccess(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		boolean result = true;
		
		HttpSession session = request.getSession();
		if(session.getAttribute("loginUser")==null) {
			
			result = false;
			
			response.setContentType("text/html;charset=utf-8");			
			PrintWriter out =response.getWriter();
			out.println("<script>");
			out.println("alert('로그인은 필수입니다.');");
			out.println("location.href='"+request.getContextPath()+"';");
			out.println("</script>");			
		}
		
		return result;
	}
}
