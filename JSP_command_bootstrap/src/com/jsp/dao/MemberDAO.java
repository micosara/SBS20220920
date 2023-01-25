package com.jsp.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.jsp.command.SearchCriteria;
import com.jsp.dto.MemberVO;

public interface MemberDAO {

	// 검색결과일반 리스트 
	List<MemberVO> selectSearchMemberList(SqlSession session,SearchCriteria cri) throws Exception;

	// 검색 결과의 전체 리스트 개수
	int selectSearchMemberListCount(SqlSession session, SearchCriteria cri) throws SQLException;

	// 회원정보 조회
	MemberVO selectMemberById(SqlSession session, String id) throws SQLException;

	// 회원 추가
	public void insertMember(SqlSession session, MemberVO member) throws SQLException;

	// 회원 수정
	public void updateMember(SqlSession session, MemberVO member) throws SQLException;

	// 회원정보 삭제
	void deleteMember(SqlSession session, String id) throws SQLException;

}
