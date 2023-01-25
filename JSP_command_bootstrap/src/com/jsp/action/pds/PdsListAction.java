package com.jsp.action.pds;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jsp.action.Action;
import com.jsp.command.SearchCriteria;
import com.jsp.service.PdsService;

public class PdsListAction implements Action {

	private PdsService pdsService;
	public void setPdsService(PdsService pdsService) {
		this.pdsService = pdsService;
	}
	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//화면결정
		String url = "/pds/list";
		
		//입력
		String page = request.getParameter("page");
		String perPageNum = request.getParameter("perPageNum");
		String searchType = request.getParameter("searchType");
		String keyword = request.getParameter("keyword");
		
		if(page==null) page="1";
		if(perPageNum==null) perPageNum="10";
		
		SearchCriteria cri = new SearchCriteria(page,perPageNum,searchType,keyword);
				
		//처리 : service		
		Map<String, Object> dataMap = pdsService.getList(cri);

		//결과 : request.setAttribute()
		request.setAttribute("dataMap", dataMap);
		
		
		return url;
	}

}
