package com.jsp.command;

public class ReplyRemoveCommand {
	
	private String bno;
	private String rno;
	private String page;
	
	public void setBno(String bno) {
		this.bno = bno;
	}	
	public void setRno(String rno) {
		this.rno = rno;
	}	
	public void setPage(String page) {
		this.page = page;
	}
	
	public int getRno() {
		return Integer.parseInt(rno);
	}
	
	public int getBno() {
		return Integer.parseInt(bno);
	}
	
	public int getPage() {
		return Integer.parseInt(page);
	}
}


