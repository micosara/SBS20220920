package com.jsp.action.member;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jsp.action.Action;
import com.jsp.command.SearchCriteria;
import com.jsp.dto.MemberVO;
import com.jsp.service.MemberService;

public class MemberListAction implements Action {
	
	private MemberService service;// = new MemberServiceImpl();
	public void setMemberService(MemberService service) { 
		this.service = service;
	}
	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String url="/member/list";
		
//		if(!LoginCheckUtils.proccess(request, response)) return;
		
		String searchType = request.getParameter("searchType");
		String keyword = request.getParameter("keyword");
		String perPageNumParam = request.getParameter("perPageNum");
		String pageParam=request.getParameter("page");
		
		SearchCriteria cri = new SearchCriteria();
		cri.setKeyword(keyword);
		cri.setSearchType(searchType);
		
		boolean criFlag = true;
		criFlag = criFlag && pageParam !=null
				          && !pageParam.isEmpty()
				          && perPageNumParam !=null
				          && !perPageNumParam.isEmpty();
		if(criFlag) {
			try {
				cri.setPage(Integer.parseInt(pageParam));
				cri.setPerPageNum(Integer.parseInt(perPageNumParam));
			}catch(Exception e) {
				response.sendError(response.SC_BAD_REQUEST);
				return null;
			}
		}
			
		
		try {
			Map<String,Object> dataMap = service.getMemberListForPage(cri);
			request.setAttribute("dataMap", dataMap);
			return url;
			
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

}
