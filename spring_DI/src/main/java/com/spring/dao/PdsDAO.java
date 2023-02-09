package com.spring.dao;

import java.sql.SQLException;
import java.util.List;

import com.jsp.command.SearchCriteria;
import com.jsp.dto.PdsVO;

public interface PdsDAO{

	List<PdsVO> selectPdsCriteria(SearchCriteria cri) throws SQLException;
	int selectPdsCriteriaTotalCount(SearchCriteria cri) throws SQLException;
	
	PdsVO selectPdsByPno(int pno) throws SQLException;
	
	void insertPds( PdsVO pds) throws SQLException;
	void updatePds( PdsVO pds) throws SQLException;
	void deletePds( int pno) throws SQLException;

	void increaseViewCnt(int pno) throws SQLException;
	
	int selectSeqNextValue() throws SQLException;
}
