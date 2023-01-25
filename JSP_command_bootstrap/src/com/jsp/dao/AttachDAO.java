package com.jsp.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.jsp.dto.AttachVO;

public interface AttachDAO {

	public List<AttachVO> selectAttachesByPno(SqlSession session, int pno)throws SQLException;
	public AttachVO selectAttachByAno(SqlSession session,int ano)throws SQLException;
	
	public void insertAttach(SqlSession session,AttachVO attach) throws SQLException;

	public void deleteAttach(SqlSession session,int ano) throws SQLException;

	public void deleteAllAttach(SqlSession session,int pno)throws SQLException;
	
}








