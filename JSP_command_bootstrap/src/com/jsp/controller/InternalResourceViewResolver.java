package com.jsp.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class InternalResourceViewResolver {

	public static void view(HttpServletRequest request, 
							HttpServletResponse response)
									throws ServletException, IOException {

		String url = (String) request.getAttribute("viewName");

		if (url == null) {
			return;
		}

		if (url.indexOf("redirect:") > -1) {

			String contextPath = request.getContextPath();

			url = contextPath + url.replace("redirect:", "");

			response.sendRedirect(url);
		} else {
			String prefix = "/WEB-INF/views";
			String subfix = ".jsp";
			url = prefix + url + subfix;
			request.getRequestDispatcher(url).forward(request, response);
		}

	}
}
