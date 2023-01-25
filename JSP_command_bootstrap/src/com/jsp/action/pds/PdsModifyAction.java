package com.jsp.action.pds;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;

import com.josephoconnell.html.HTMLInputFilter;
import com.jsp.action.Action;
import com.jsp.action.utils.GetUploadPath;
import com.jsp.action.utils.MultipartHttpServletRequestParser;
import com.jsp.controller.FileUploadResolver;
import com.jsp.dto.AttachVO;
import com.jsp.dto.PdsVO;
import com.jsp.exception.NotMultipartFormDataException;
import com.jsp.service.PdsService;

public class PdsModifyAction implements Action {

	private PdsService pdsService;

	public void setPdsService(PdsService pdsService) {
		this.pdsService = pdsService;
	}

	// 업로드 파일 환경 설정
	private static final int MEMORY_THRESHOLD = 1024 * 1024 * 3; // 3MB
	private static final int MAX_FILE_SIZE = 1024 * 1024 * 40; // 40MB
	private static final int MAX_REQUEST_SIZE = 1024 * 1024 * 200; // 200MB

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String url = "/pds/modify_success";

		MultipartHttpServletRequestParser multi = null;
		List<AttachVO> attachList = null;

		try {
			multi = new MultipartHttpServletRequestParser(request, MEMORY_THRESHOLD, MAX_FILE_SIZE, MAX_REQUEST_SIZE);

			// 파일 삭제 및 DB삭제
			String[] deleteFiles = multi.getParameterValues("deleteFile");
			if (deleteFiles != null && deleteFiles.length > 0) {
				for (String anoStr : deleteFiles) {
					int ano = Integer.parseInt(anoStr);
					AttachVO attach = pdsService.getAttachByAno(ano);
					String filePath = attach.getUploadPath() + File.separator + attach.getFileName();
					File targetFile = new File(filePath);
					if (targetFile.exists()) {
						targetFile.delete(); // 파일 삭제
					}
					pdsService.removeAttachByAno(ano); // DB 삭제
				}
			}

			// 추가된 파일 저장
			FileItem[] fileItems = multi.getFileItems("uploadFile");
			if (fileItems != null && fileItems.length > 0) {
				// 파일저장
				String uploadPath = GetUploadPath.getUploadPath("pds.upload");
				List<File> fileList = FileUploadResolver.fileUpload(fileItems, uploadPath);

				// attaches
				attachList = new ArrayList<AttachVO>();
				if (fileList.size() > 0)
					for (File file : fileList) {
						AttachVO attach = new AttachVO();
						attach.setFileName(file.getName());
						attach.setUploadPath(uploadPath);
						attach.setFileType(file.getName().substring(file.getName().lastIndexOf(".") + 1));
						attachList.add(attach);
						attach.setPno(Integer.parseInt(multi.getParameter("pno")));
						attach.setAttacher(multi.getParameter("writer"));
					}
			}
		} catch (NotMultipartFormDataException e) {
			e.printStackTrace();
			response.sendError(HttpServletResponse.SC_BAD_REQUEST);
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}

		PdsVO pds = new PdsVO();
		pds.setPno(Integer.parseInt(multi.getParameter("pno")));
		String title = HTMLInputFilter.htmlSpecialChars(multi.getParameter("title")); 
		pds.setTitle(title);
		pds.setWriter(multi.getParameter("writer"));
		pds.setContent(multi.getParameter("content"));
		pds.setAttachList(attachList);

		try {

			// DB 수정
			pdsService.modify(pds);

			request.setAttribute("pds", pds);

			return url;

		} catch (Exception e) {
			// Exception 처리
			e.printStackTrace();
			throw e;
		}

	}

}
