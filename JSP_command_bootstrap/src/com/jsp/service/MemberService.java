package com.jsp.service;

import java.util.Map;

import com.jsp.command.SearchCriteria;
import com.jsp.dto.MemberVO;

public interface MemberService {

	// 회원리스트
	public Map<String,Object> getMemberListForPage(SearchCriteria cri)
													throws Exception;
	
	// 회원상세
	public MemberVO getMember(String id) throws Exception;

	// 회원등록
	public void regist(MemberVO member) throws Exception;

	// 회원수정
	public void modify(MemberVO member) throws Exception;

	// 회원탈퇴
	public void remove(String id) throws Exception;

}
