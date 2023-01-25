package com.jsp.action.member;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jsp.action.Action;
import com.jsp.dto.MemberVO;
import com.jsp.service.MemberService;

public class MemberDetailAction implements Action{
	
	private MemberService memberService;
	public void setMemberService(MemberService memberService) {
		this.memberService=memberService;
	}
	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String url = "/member/detail";

		String id = request.getParameter("id");
		try {
			MemberVO member = memberService.getMember(id);
			request.setAttribute("member", member);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return url;
	}

}
