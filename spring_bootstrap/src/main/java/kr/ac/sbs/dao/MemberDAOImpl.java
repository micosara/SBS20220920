package kr.ac.sbs.dao;

import java.sql.SQLException;

import org.apache.ibatis.session.SqlSession;

import com.jsp.dto.MemberVO;

public class MemberDAOImpl extends com.spring.dao.MemberDAOImpl
							   implements MemberDAO{

	private SqlSession session;	
	public void setSession(SqlSession session) {
		this.session = session;
	}
	
	@Override
	public MemberVO selectMemberByPicture(String picture) throws SQLException {
		MemberVO member=null;		
		member = session.selectOne("Member-Mapper.selectMemberByPicture",picture);		
		return member;
	}

}
