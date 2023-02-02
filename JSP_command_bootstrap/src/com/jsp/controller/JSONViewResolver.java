package com.jsp.controller;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

public class JSONViewResolver {
	public static void view(HttpServletResponse response, Object target) throws Exception {

		// 출력
		ObjectMapper mapper = new ObjectMapper();

		//	content Type 결정
		response.setContentType("application/json;charset=utf-8");
		PrintWriter out = response.getWriter();

		// 내보내기
		out.println(mapper.writeValueAsString(target));

		// out 객체를 종료하고 환원.
		out.close();
	}
}
