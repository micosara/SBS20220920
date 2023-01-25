package com.jsp.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;

import com.jsp.command.SearchCriteria;
import com.jsp.dto.PdsVO;

public class PdsDAOImpl implements PdsDAO{

	@Override
	public List<PdsVO> selectPdsCriteria(SqlSession session, SearchCriteria cri) throws SQLException {

		int offset=cri.getStartRowNum();
		int limit=cri.getPerPageNum();
		RowBounds rowBounds=new RowBounds(offset,limit);
		
		List<PdsVO> pdsList=
		   session.selectList("Pds-Mapper.selectSearchPdsList",cri,rowBounds);	
			
		return pdsList;
	}

	@Override
	public int selectPdsCriteriaTotalCount(SqlSession session, SearchCriteria cri) throws SQLException {
		int count=session.selectOne("Pds-Mapper.selectSearchPdsListCount",cri);
		return count;
	}

	@Override
	public void insertPds(SqlSession session, PdsVO pds) throws SQLException {		
		session.update("Pds-Mapper.insertPds",pds);		
	}

	@Override
	public int selectSeqNextValue(SqlSession session) throws SQLException {
		int pno=session.selectOne("Pds-Mapper.selectPdsSeqNext");
		return pno;
	}

	@Override
	public PdsVO selectPdsByPno(SqlSession session, int pno) throws SQLException {
		PdsVO pds=session.selectOne("Pds-Mapper.selectPdsByPno",pno);
		return pds;
	}

	@Override
	public void increaseViewCnt(SqlSession session, int pno) throws SQLException {
		session.update("Pds-Mapper.increaseViewCnt",pno);
	}
	
	@Override
	public void updatePds(SqlSession session,PdsVO pds) throws SQLException {
		session.update("Pds-Mapper.updatePds",pds);
	}

	@Override
	public void deletePds(SqlSession session,int pno) throws SQLException {
		session.update("Pds-Mapper.deletePds",pno);
	}
}






