package com.jsp.command;

import com.jsp.dto.NoticeVO;

public class NoticeModifyCommand {
	
	private String nno;
	private String title;
	private String content;
	private String writer;
	
	public String getNno() {
		return nno;
	}
	public void setNno(String nno) {
		this.nno = nno;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	
	public NoticeVO toNoticeVO(){
		NoticeVO notice = new NoticeVO();
		
		notice.setNno(Integer.parseInt(this.nno));
		notice.setTitle(this.title);
		notice.setContent(this.content);
		notice.setWriter(this.writer);	
		
		return notice;
		
	}
}



