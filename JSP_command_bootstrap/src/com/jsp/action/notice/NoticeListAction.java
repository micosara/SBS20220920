package com.jsp.action.notice;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jsp.action.Action;
import com.jsp.command.SearchCriteria;
import com.jsp.command.SearchCriteriaCommand;
import com.jsp.controller.HttpRequestParameterAdapter;
import com.jsp.service.NoticeService;

public class NoticeListAction implements Action {

	private NoticeService noticeService;

	public void setNoticeService(NoticeService noticeService) {
		this.noticeService = noticeService;
	}

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String url = "/notice/list";

		try {
			SearchCriteriaCommand command 
				= HttpRequestParameterAdapter.execute(request, SearchCriteriaCommand.class);
			
			SearchCriteria cri = command.toSearchCriteria();

			// 처리
			Map<String, Object> dataMap = noticeService.getNoticeList(cri);
			request.setAttribute("dataMap", dataMap);

			return url;
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

}
