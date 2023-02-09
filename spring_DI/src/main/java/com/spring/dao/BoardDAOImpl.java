package com.spring.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.jsp.command.SearchCriteria;
import com.jsp.dto.BoardVO;

public class BoardDAOImpl implements BoardDAO{

	private SqlSession session;
	private com.jsp.dao.BoardDAO boardDAO;
	
	
	public void setSession(SqlSession session) {
		this.session = session;
	}
	public void setBoardDAO(com.jsp.dao.BoardDAO boardDAO) {
		this.boardDAO = boardDAO;
	}

	@Override
	public List<BoardVO> selectSearchBoardList(SearchCriteria cri) throws SQLException {		
		return boardDAO.selectSearchBoardList(session, cri);
	}

	@Override
	public int selectSearchBoardListCount(SearchCriteria cri) throws SQLException {		
		return boardDAO.selectSearchBoardListCount(session, cri);
	}

	@Override
	public BoardVO selectBoardByBno(int bno) throws SQLException {
		return boardDAO.selectBoardByBno(session, bno);
	}

	@Override
	public void insertBoard(BoardVO board) throws SQLException {
		boardDAO.insertBoard(session, board);		
	}

	@Override
	public void updateBoard(BoardVO board) throws SQLException {
		boardDAO.updateBoard(session, board);
	}

	@Override
	public void deleteBoard(int bno) throws SQLException {
		boardDAO.deleteBoard(session, bno);		
	}

	@Override
	public void increaseViewCnt(int bno) throws SQLException {
		boardDAO.increaseViewCnt(session, bno);
	}

	@Override
	public int selectBoardSeqNext() throws SQLException {	
		return boardDAO.selectBoardSeqNext(session);
	}

}
