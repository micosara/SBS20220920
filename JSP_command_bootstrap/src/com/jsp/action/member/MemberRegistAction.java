package com.jsp.action.member;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jsp.action.Action;
import com.jsp.dto.MemberVO;
import com.jsp.service.MemberService;

public class MemberRegistAction implements Action{
	
	private MemberService service;// = new MemberServiceImpl();
	public void setMemberService(MemberService service) { 
		this.service = service;
	}
	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String url="/member/regist_success";		
		//request.setCharacterEncoding("UTF-8");		
		
		String[] phoneArray=request.getParameterValues("phone");
		String phone = phoneArray[0]+phoneArray[1]+phoneArray[2];
		
		MemberVO member = new MemberVO();
		member.setId(request.getParameter("id"));
		member.setPwd(request.getParameter("pwd"));
		member.setName(request.getParameter("name"));
		member.setEmail(request.getParameter("email"));
		member.setPicture(request.getParameter("picture"));
		member.setAuthority(request.getParameter("authority"));
		member.setPhone(phone);
		
		
		try {
			service.regist(member);			
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		
		
		return url;
	}

}
