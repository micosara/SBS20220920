package com.jsp.command;

import com.jsp.dto.BoardVO;

public class BoardModifyCommand {
	
	private String bno;
	private String title;
	private String content;
	private String writer;
	
	public String getBno() {
		return bno;
	}
	public void setBno(String bno) {
		this.bno = bno;
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
	
	public BoardVO toBoardVO(){
		BoardVO notice = new BoardVO();
		
		notice.setBno(Integer.parseInt(this.bno));
		notice.setTitle(this.title);
		notice.setContent(this.content);
		notice.setWriter(this.writer);	
		
		return notice;
		
	}
}



