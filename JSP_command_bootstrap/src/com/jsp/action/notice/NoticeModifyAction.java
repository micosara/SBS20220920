package com.jsp.action.notice;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.josephoconnell.html.HTMLInputFilter;
import com.jsp.action.Action;
import com.jsp.command.NoticeModifyCommand;
import com.jsp.controller.HttpRequestParameterAdapter;
import com.jsp.dto.NoticeVO;
import com.jsp.service.NoticeService;

public class NoticeModifyAction implements Action {

	private NoticeService noticeService;

	public void setNoticeService(NoticeService noticeService) {
		this.noticeService = noticeService;
	}

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String url = "redirect:/notice/detail.do?nno=" + request.getParameter("nno");

		// 입력
		NoticeModifyCommand command 
			= HttpRequestParameterAdapter.execute(request, NoticeModifyCommand.class);
		 
		NoticeVO notice = command.toNoticeVO();

		String title = HTMLInputFilter.htmlSpecialChars(notice.getTitle());
		notice.setTitle(title);

		// 처리 (기능)
		noticeService.modify(notice);
		
		return url;
	}

}
