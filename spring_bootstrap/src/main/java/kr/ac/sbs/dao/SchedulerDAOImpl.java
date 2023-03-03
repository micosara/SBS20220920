package kr.ac.sbs.dao;

import java.sql.SQLException;

import org.apache.ibatis.session.SqlSession;

import com.jsp.dto.BoardVO;
import com.jsp.dto.NoticeVO;
import com.jsp.dto.PdsVO;

public class SchedulerDAOImpl implements SchedulerDAO {
	
	private SqlSession session;
	public void setSession(SqlSession session) {
		this.session = session;
	}
	
	@Override
	public NoticeVO selectNoticeByImage(String imageFile) throws SQLException {
		NoticeVO notice = session.selectOne("Notice-Mapper.selectNoticeByImage",imageFile);
		return notice;
	}

	@Override
	public PdsVO selectPdsByImage(String imageFile) throws SQLException {
		PdsVO pds = session.selectOne("Pds-Mapper.selectPdsByImage",imageFile);
		return pds;	
	}

	@Override
	public BoardVO selectBoardByImage(String imageFile) throws SQLException {
		BoardVO board = session.selectOne("Board-Mapper.selectBoardByImage",imageFile);
		return board;
	}

}
