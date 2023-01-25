package com.jsp.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.jsp.dto.AttachVO;

public class AttachDAOImpl implements AttachDAO{
	
	
	@Override
	public List<AttachVO> selectAttachesByPno(SqlSession session,int pno) throws SQLException {
		List<AttachVO> attachList=session.selectList("Attach-Mapper.selectAttachByPno",pno);
		return attachList;
	}
	
	@Override
	public AttachVO selectAttachByAno(SqlSession session,int ano) throws SQLException {
		AttachVO attach=session.selectOne("Attach-Mapper.selectAttachByAno",ano);
		return attach;
	}
	
	@Override
	public void insertAttach(SqlSession session,AttachVO attach) throws SQLException {
		session.update("Attach-Mapper.insertAttach",attach);
	}

	@Override
	public void deleteAttach(SqlSession session,int ano) throws SQLException {
		session.update("Attach-Mapper.deleteAttach",ano);		
		
	}
	
	@Override
	public void deleteAllAttach(SqlSession session,int pno) throws SQLException {
		session.update("Attach-Mapper.deleteAllAttach",pno);		
	}
	
	
}
