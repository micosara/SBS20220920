package kr.ac.sbs.dao;

import java.sql.SQLException;

import com.jsp.dto.MemberVO;

public interface MemberDAO extends com.spring.dao.MemberDAO{
	
	// 회원정보 조회
	MemberVO selectMemberByPicture(String picture) throws SQLException;
}
