package kr.ac.sbs.command;

import java.util.Date;

import com.jsp.dto.MemberVO;

public class MemberRegistCommand {
	
	private String id;  //아이디
	private String pwd; //패스워드
	private String name; //이름
	private String[] phone; //전화번호
	private String email;  //이메일
	private String picture; // 사진파일 경로/파일명
	private String authority; // 권한
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String[] getPhone() {
		return phone;
	}
	public void setPhone(String[] phone) {
		this.phone = phone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPicture() {
		return picture;
	}
	public void setPicture(String picture) {
		this.picture = picture;
	}
	public String getAuthority() {
		return authority;
	}
	public void setAuthority(String authority) {
		this.authority = authority;
	}
	
	public MemberVO toMemberVO() {
		String phone = "";
		for (String data : this.phone) {
			phone += data;
		}
		
		// MemberVO setting
		MemberVO member = new MemberVO();
		member.setId(id);
		member.setPwd(pwd);
		member.setPhone(phone);
		member.setEmail(email);
		member.setPicture(picture);
		member.setAuthority(authority);
		member.setName(name);
		
		return member;
	}
	
}
