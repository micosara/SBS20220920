package com.jsp.action.notice;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.josephoconnell.html.HTMLInputFilter;
import com.jsp.action.Action;
import com.jsp.controller.HttpRequestParameterAdapter;
import com.jsp.dto.NoticeVO;
import com.jsp.service.NoticeService;

public class NoticeRegistAction implements Action{
	
	private NoticeService noticeService;
	public void setNoticeService(NoticeService noticeService) {
		this.noticeService = noticeService;
	}
	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//화면
		String url="/notice/regist_success";
		
		//입력
		NoticeVO notice = HttpRequestParameterAdapter.execute(request, NoticeVO.class);
		
		String title = HTMLInputFilter.htmlSpecialChars(notice.getTitle()); 
		notice.setTitle(title);
		
		//처리 (기능)
		noticeService.regist(notice);
					
		//출력
		return url;
	}

}






