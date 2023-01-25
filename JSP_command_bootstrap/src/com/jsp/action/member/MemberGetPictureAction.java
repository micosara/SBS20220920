package com.jsp.action.member;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jsp.action.Action;
import com.jsp.action.utils.GetUploadPath;
import com.jsp.controller.FileDownloadResolver;
import com.jsp.dto.MemberVO;
import com.jsp.service.MemberService;

public class MemberGetPictureAction implements Action{
	

	private MemberService memberService;
	public void setSearchMemberService(MemberService memberService) {
		this.memberService=memberService;
	}
	
	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String url=null;
		
		
		String id = request.getParameter("id");
		try {
			MemberVO member = memberService.getMember(id);
			
			String fileName = member.getPicture();		
			String savedPath = GetUploadPath.getUploadPath("member.picture.upload");	
			
			FileDownloadResolver.sendFile(fileName,savedPath,request,response);
		
		}catch(Exception e) {
			e.printStackTrace();
			response.sendError(response.SC_INTERNAL_SERVER_ERROR);
		}
		
		return url;
	}

}







