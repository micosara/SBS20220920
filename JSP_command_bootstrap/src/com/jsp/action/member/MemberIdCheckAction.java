package com.jsp.action.member;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jsp.action.Action;
import com.jsp.dto.MemberVO;
import com.jsp.service.MemberService;

public class MemberIdCheckAction implements Action{

	private MemberService memberService;
	public void setSearchMemberService(MemberService memberService) {
		this.memberService=memberService;
	}
	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String url=null;
		
		String resultStr = "";		
		String id=request.getParameter("id");
		
		MemberVO member = memberService.getMember(id);
		if(member!=null) {
			resultStr="DUPLICATED";
		}		
		
		response.setContentType("text/plain;charset=utf-8");
		PrintWriter out=response.getWriter();
		out.print(resultStr);
		out.close();
		
		return url;
	}

}




