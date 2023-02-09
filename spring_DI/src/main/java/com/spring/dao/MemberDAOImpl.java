package com.spring.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.jsp.command.SearchCriteria;
import com.jsp.dto.MemberVO;

public class MemberDAOImpl implements MemberDAO{

	private SqlSession session;
	private com.jsp.dao.MemberDAO memberDAO;
	
	public void setSession(SqlSession session) {
		this.session = session;
	}
	public void setMemberDAO(com.jsp.dao.MemberDAO memberDAO) {
		this.memberDAO = memberDAO;
	}

	@Override
	public List<MemberVO> selectSearchMemberList(SearchCriteria cri) throws SQLException {
		try {
			return memberDAO.selectSearchMemberList(session, cri);
		} catch (Exception e) {
			e.printStackTrace();
			throw (SQLException)e;
		}
	}

	@Override
	public int selectSearchMemberListCount(SearchCriteria cri) throws SQLException {
		return memberDAO.selectSearchMemberListCount(session, cri);
	}

	@Override
	public MemberVO selectMemberById(String id) throws SQLException {
		return memberDAO.selectMemberById(session, id);
	}

	@Override
	public void insertMember(MemberVO member) throws SQLException {
		memberDAO.insertMember(session, member);
	}

	@Override
	public void updateMember(MemberVO member) throws SQLException {
		memberDAO.updateMember(session, member);
	}

	@Override
	public void deleteMember(String id) throws SQLException {
		memberDAO.deleteMember(session, id);
	}

}
