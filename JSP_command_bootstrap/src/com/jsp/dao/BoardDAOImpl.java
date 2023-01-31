package com.jsp.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;

import com.jsp.command.SearchCriteria;
import com.jsp.dto.BoardVO;

public class BoardDAOImpl implements BoardDAO{

	@Override
	public List<BoardVO> selectSearchBoardList(SqlSession session, SearchCriteria cri) throws SQLException {
		
		int offset=cri.getStartRowNum();
		int limit=cri.getPerPageNum();		
		RowBounds rowBounds=new RowBounds(offset,limit);		
		
		List<BoardVO> boardList=
				session.selectList("Board-Mapper.selectSearchBoardList",cri,rowBounds);
		
		return boardList;
	}

	@Override
	public int selectSearchBoardListCount(SqlSession session, SearchCriteria cri) throws SQLException {
		int count=session.selectOne("Board-Mapper.selectSearchBoardListCount",cri);
		return count;
	}

	@Override
	public BoardVO selectBoardByBno(SqlSession session, int bno) throws SQLException {
		BoardVO board=session.selectOne("Board-Mapper.selectBoardByBno",bno);
		return board;
	}

	@Override
	public void insertBoard(SqlSession session,BoardVO board) throws SQLException {
		session.update("Board-Mapper.insertBoard",board);
	}

	@Override
	public void updateBoard(SqlSession session,BoardVO board) throws SQLException {
		session.update("Board-Mapper.updateBoard",board);
	}

	@Override
	public void deleteBoard(SqlSession session,int bno) throws SQLException {
		session.update("Board-Mapper.deleteBoard",bno);
	}

	@Override
	public void increaseViewCnt(SqlSession session,int bno) throws SQLException {
		session.update("Board-Mapper.increaseViewCnt",bno);
	}

	@Override
	public int selectBoardSeqNext(SqlSession session) throws SQLException {
		int seq_num=session.selectOne("Board-Mapper.selectBoardSeqNext");
		return seq_num;
	}
}
