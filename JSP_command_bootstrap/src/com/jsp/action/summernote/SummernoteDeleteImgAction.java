package com.jsp.action.summernote;

import java.io.File;
import java.io.PrintWriter;
import java.net.URLDecoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jsp.action.Action;
import com.jsp.action.utils.GetUploadPath;
import com.jsp.command.SummernoteDeleteImgCommand;

public class SummernoteDeleteImgAction implements Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String url = null;

		ObjectMapper mapper = new ObjectMapper();
		SummernoteDeleteImgCommand delReq = mapper.readValue(request.getReader(), SummernoteDeleteImgCommand.class);

		// System.out.println(delReq.getFileName());

		String savePath = GetUploadPath.getUploadPath("summernote.img");
		String fileName = URLDecoder.decode(delReq.getFileName(), "utf-8");

		// System.out.println(fileName);

		File target = new File(savePath + File.separator + fileName);

		if (target.exists()) {
			target.delete();
			
			response.setContentType("text/plain;charset=utf-8");
			PrintWriter out = response.getWriter();
			out.print(fileName + " 이미지를 삭제했습니다.");
		} else {
			response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		}
		return url;
	}

}
