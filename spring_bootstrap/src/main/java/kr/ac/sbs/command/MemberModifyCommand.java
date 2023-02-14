package kr.ac.sbs.command;

import org.springframework.web.multipart.MultipartFile;

import com.jsp.dto.MemberVO;

public class MemberModifyCommand {
	
	private String id;  //아이디
	private String pwd; //패스워드
	private String name; //이름
	private String phone; //전화번호
	private String email;  //이메일
	private String authority; //권한
	private MultipartFile picture; // 사진파일	
	
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
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getAuthority() {
		return authority;
	}
	public void setAuthority(String authority) {
		this.authority = authority;
	}
	public MultipartFile getPicture() {
		return picture;
	}
	public void setPicture(MultipartFile picture) {
		this.picture = picture;
	}
	
	public MemberVO toMember() {
		MemberVO member = new MemberVO();
		member.setId(this.id);
		member.setPwd(this.pwd);
		member.setName(this.name);
		member.setPhone(this.phone.replace("-", ""));
		member.setEmail(this.email);
		member.setAuthority(this.authority);
			
		return member;
	}	
	
}
