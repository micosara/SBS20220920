package com.jsp.action.pds;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jsp.action.Action;
import com.jsp.controller.FileDownloadResolver;
import com.jsp.dto.AttachVO;
import com.jsp.service.PdsService;

public class PdsGetFileAction implements Action{
	
	private PdsService pdsService;	
	public void setPdsService(PdsService pdsService) {
		this.pdsService = pdsService;
	}
	
	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String url=null;
		
		int ano = Integer.parseInt(request.getParameter("ano"));
		
		AttachVO attach = pdsService.getAttachByAno(ano);
		
		String fileName = attach.getFileName();
		String savedPath = attach.getUploadPath();
		
		FileDownloadResolver.sendFile(fileName, savedPath, request, response);
		
		
		return url;
	}

}







