package com.spring.dao;

import java.sql.SQLException;
import java.util.List;

import com.jsp.dto.AttachVO;

public interface AttachDAO {

	public List<AttachVO> selectAttachesByPno(int pno) throws SQLException;

	public AttachVO selectAttachByAno(int ano) throws SQLException;

	public void insertAttach(AttachVO attach) throws SQLException;

	public void deleteAttach(int ano) throws SQLException;

	public void deleteAllAttach(int pno) throws SQLException;
}
