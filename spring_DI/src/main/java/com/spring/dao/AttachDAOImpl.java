package com.spring.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.jsp.dto.AttachVO;

public class AttachDAOImpl implements AttachDAO{

	private SqlSession session;
	private com.jsp.dao.AttachDAO attachDAO;
	
	
	public void setSession(SqlSession session) {
		this.session = session;
	}
	public void setAttachDAO(com.jsp.dao.AttachDAO attachDAO) {
		this.attachDAO = attachDAO;
	}

	@Override
	public List<AttachVO> selectAttachesByPno(int pno) throws SQLException {
		return attachDAO.selectAttachesByPno(session, pno);
	}

	@Override
	public AttachVO selectAttachByAno(int ano) throws SQLException {
		return attachDAO.selectAttachByAno(session, ano);
	}

	@Override
	public void insertAttach(AttachVO attach) throws SQLException {
		attachDAO.insertAttach(session, attach);
	}

	@Override
	public void deleteAttach(int ano) throws SQLException {
		attachDAO.deleteAttach(session, ano);
	}

	@Override
	public void deleteAllAttach(int pno) throws SQLException {
		attachDAO.deleteAllAttach(session, pno);
	}

}
