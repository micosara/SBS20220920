package com.jsp.action.summernote;

import java.io.File;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jsp.action.Action;
import com.jsp.action.utils.GetUploadPath;
import com.jsp.action.utils.MultipartHttpServletRequestParser;
import com.jsp.controller.FileUploadResolver;
import com.jsp.exception.NotMultipartFormDataException;

public class SummernoteUploadImgAction implements Action{

	// 업로드 파일 환경 설정
	private static final int MEMORY_THRESHOLD = 1024 * 500; // 500KB
	private static final int MAX_FILE_SIZE = 1024 * 1024 * 5; // 5MB
	private static final int MAX_REQUEST_SIZE = 1024 * 1024 * 10; // 10MB
	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String url=null;
		
		// request parsing
		MultipartHttpServletRequestParser multi; 
		try {
			multi= new MultipartHttpServletRequestParser(request, MEMORY_THRESHOLD,
												   MAX_FILE_SIZE, MAX_REQUEST_SIZE);
		}catch(NotMultipartFormDataException e) {
			e.printStackTrace();
			response.sendError(response.SC_BAD_REQUEST);
			return null;
		}
		
		// upload path
		String uploadPath = GetUploadPath.getUploadPath("summernote.img"); 
		
		// file save : get file list
		List<File> fileList 
					= FileUploadResolver.fileUpload(multi.getFileItems("file"), uploadPath);
		
		// response out
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		
		File file = fileList.get(0);
		out.print(request.getContextPath() + "/getImg.do?fileName="+ file.getName());
		
		return url;
	}

}




