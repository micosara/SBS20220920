package com.jsp.action.member;

import java.io.File;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;

import com.jsp.action.Action;
import com.jsp.action.utils.GetUploadPath;
import com.jsp.action.utils.MultipartHttpServletRequestParser;
import com.jsp.controller.FileUploadResolver;
import com.jsp.dto.MemberVO;
import com.jsp.exception.NotMultipartFormDataException;
import com.jsp.service.MemberService;

public class MemberModifyAction implements Action{

	private MemberService service;
	public void setMemberService(MemberService service) {
		this.service = service;
	}
	
	// 업로드 파일 환경 설정
	private static final int MEMORY_THRESHOLD = 1024 * 500; // 500KB
	private static final int MAX_FILE_SIZE = 1024 * 1024 * 1; // 1MB
	private static final int MAX_REQUEST_SIZE = 1024 * 1024 * 2; // 2MB
	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String url="/member/modify_success";
		

		MultipartHttpServletRequestParser multiReq = null;
		try {
		 multiReq = new MultipartHttpServletRequestParser(request,
				 				MEMORY_THRESHOLD, MAX_FILE_SIZE, MAX_REQUEST_SIZE);
		}catch (NotMultipartFormDataException e) {
			response.sendError(response.SC_BAD_REQUEST);
			return null;
		}

		String id = multiReq.getParameter("id");
		String pwd = multiReq.getParameter("pwd");
		String email = multiReq.getParameter("email");
		String authority = multiReq.getParameter("authority");
		String name = multiReq.getParameter("name");
		String phone = multiReq.getParameter("phone");
		
		MemberVO member = new MemberVO();
		member.setId(id);
		member.setPwd(pwd);
		member.setPhone(phone);
		member.setEmail(email);
		member.setAuthority(authority);
		member.setName(name);
		
		FileItem picture = multiReq.getFileItem("picture");
		if(picture.getSize()>0) { //사진변경
			//저장경로
			String uploadPath = GetUploadPath.getUploadPath("member.picture.upload");
						
			//기존 사진이미지 삭제
			String oldPicture = multiReq.getParameter("oldPicture");
			File deleteFile = new File(uploadPath,oldPicture);
			if(deleteFile.exists()) {
				deleteFile.delete();
			}
			
			//최근 사진이미지 저장
			List<File> fileList 
			=  FileUploadResolver.fileUpload(multiReq.getFileItems("picture"),uploadPath);
			File saveFile = fileList.get(0);
						
			//최근 사진이미지 파일명 vo에 추가
			member.setPicture(saveFile.getName());
		}else { //기존 사진 유지
			member.setPicture(multiReq.getParameter("oldPicture"));
		}
		try {
			service.modify(member);
			request.setAttribute("member", service.getMember(member.getId()));
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		
		return url;
	}

}
