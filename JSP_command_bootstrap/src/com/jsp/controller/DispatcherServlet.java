package com.jsp.controller;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jsp.action.Action;

public class DispatcherServlet extends HttpServlet {

	private HandlerMapper handlerMapper;

	@Override
	public void init(ServletConfig config) throws ServletException {
		// System.out.println("DispatcherSevlet loading..........");
		try {
			handlerMapper = new HandlerMapper();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		requestPro(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		requestPro(request, response);
	}

	private void requestPro(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// 사용자 URI 검출
		String command = request.getRequestURI(); // contextPath 포함.
		if (command.indexOf(request.getContextPath()) == 0) { // contextPath 삭제
			command = command.substring(request.getContextPath().length());
		}

		Action action = null;
		String view = null;

		if (handlerMapper != null) {
			action = handlerMapper.getAction(command);

			try {
				if (action != null) {
					view = action.execute(request, response);

					if (view == null) {
						return;
					}

					// forward / redirect
					request.setAttribute("viewName", view);
					InternalResourceViewResolver.view(request, response);
				} else {
					response.sendError(HttpServletResponse.SC_NOT_FOUND);
				}
			} catch (Exception e) {
				e.printStackTrace();
				response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			}
		}else {
			response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		}

	}

}
