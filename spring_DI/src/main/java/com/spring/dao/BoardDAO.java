package com.spring.dao;

import java.sql.SQLException;
import java.util.List;

import com.jsp.command.SearchCriteria;
import com.jsp.dto.BoardVO;

public interface BoardDAO {

	List<BoardVO> selectSearchBoardList(SearchCriteria cri) throws SQLException;

	int selectSearchBoardListCount(SearchCriteria cri) throws SQLException;

	BoardVO selectBoardByBno(int bno) throws SQLException;	

	void insertBoard(BoardVO board) throws SQLException;

	void updateBoard(BoardVO board) throws SQLException;

	void deleteBoard(int bno) throws SQLException;

	void increaseViewCnt(int bno) throws SQLException;

	int selectBoardSeqNext() throws SQLException;
}
