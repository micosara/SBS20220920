package com.jsp.action.pds;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jsp.action.Action;
import com.jsp.action.utils.MakeFileName;
import com.jsp.dto.AttachVO;
import com.jsp.dto.PdsVO;
import com.jsp.service.PdsService;

public class PdsModifyFormAction implements Action {

	public PdsService pdsService;

	public void setPdsService(PdsService pdsService) {
		this.pdsService = pdsService;

	}

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String url = "/pds/modify";

		int pno = Integer.parseInt(request.getParameter("pno"));

		try {
			PdsVO pds = pdsService.getPds(pno);

			List<AttachVO> renamedAttachList=
					MakeFileName.parseFileNameFromAttaches(pds.getAttachList(), "\\$\\$");
			pds.setAttachList(renamedAttachList);
			
			request.setAttribute("pds", pds);

			return url;
		} catch (Exception e) {
			// error 작성
			e.printStackTrace();
			throw e;
		}
	}

}
