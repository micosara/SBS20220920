package com.jsp.action.pds;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jsp.action.Action;
import com.jsp.action.utils.MakeFileName;
import com.jsp.dto.AttachVO;
import com.jsp.dto.PdsVO;
import com.jsp.service.PdsService;

public class PdsDetailAction implements Action{

	private PdsService pdsService;
	public void setPdsService(PdsService pdsService) {
		this.pdsService = pdsService;
	}
	
	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String url="/pds/detail";

		int pno = Integer.parseInt(request.getParameter("pno"));
		String from = request.getParameter("from");
		
		try {
			PdsVO pds = null;
			if(from!=null && from.equals("list")) {
				pds = pdsService.read(pno);
				
				url="redirect:/pds/detail.do?pno="+pno;
			}else {				
				pds = pdsService.getPds(pno);
			}			
			

			List<AttachVO> renamedAttachList=
					MakeFileName.parseFileNameFromAttaches(pds.getAttachList(), "\\$\\$");
			pds.setAttachList(renamedAttachList);
			
			request.setAttribute("pds", pds);
		
			return url;
			
		} catch (Exception e) {			
			e.printStackTrace();			
			url=null;
			throw e;
		}	
	}

}






