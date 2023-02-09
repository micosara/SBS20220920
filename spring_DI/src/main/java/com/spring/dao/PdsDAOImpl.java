package com.spring.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.jsp.command.SearchCriteria;
import com.jsp.dto.PdsVO;

public class PdsDAOImpl implements PdsDAO{

	private SqlSession session;
	private com.jsp.dao.PdsDAO pdsDAO;
	
	
	public void setSession(SqlSession session) {
		this.session = session;
	}
	public void setPdsDAO(com.jsp.dao.PdsDAO pdsDAO) {
		this.pdsDAO = pdsDAO;
	}

	
	@Override
	public List<PdsVO> selectPdsCriteria(SearchCriteria cri) throws SQLException {
		return pdsDAO.selectPdsCriteria(session, cri);
	}

	@Override
	public int selectPdsCriteriaTotalCount(SearchCriteria cri) throws SQLException {
		return pdsDAO.selectPdsCriteriaTotalCount(session, cri);
	}

	@Override
	public PdsVO selectPdsByPno(int pno) throws SQLException {
		return pdsDAO.selectPdsByPno(session, pno);
	}

	@Override
	public void insertPds(PdsVO pds) throws SQLException {
		pdsDAO.insertPds(session, pds);
	}

	@Override
	public void updatePds(PdsVO pds) throws SQLException {
		pdsDAO.updatePds(session, pds);
	}

	@Override
	public void deletePds(int pno) throws SQLException {
		pdsDAO.deletePds(session, pno);
	}

	@Override
	public void increaseViewCnt(int pno) throws SQLException {
		pdsDAO.increaseViewCnt(session, pno);
	}

	@Override
	public int selectSeqNextValue() throws SQLException {
		return pdsDAO.selectSeqNextValue(session);
	}

}
