package com.spring.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.jsp.command.SearchCriteria;
import com.jsp.dto.ReplyVO;

public class ReplyDAOImpl implements ReplyDAO{

	private SqlSession session;
	private com.jsp.dao.ReplyDAO replyDAO;
	
	public void setSession(SqlSession session) {
		this.session = session;
	}
	public void setReplyDAO(com.jsp.dao.ReplyDAO replyDAO) {
		this.replyDAO = replyDAO;
	}

	@Override
	public void insertReply(ReplyVO reply) throws SQLException {
		replyDAO.insertReply(session, reply);
	}

	@Override
	public void updateReply(ReplyVO reply) throws SQLException {
		replyDAO.updateReply(session, reply);
	}

	@Override
	public void deleteReply(int rno) throws SQLException {
		replyDAO.deleteReply(session, rno);
	}

	@Override
	public int selectReplySeqNextValue() throws SQLException {
		return replyDAO.selectReplySeqNextValue(session);
	}

	@Override
	public List<ReplyVO> selectReplyListPage(int bno, SearchCriteria cri) throws SQLException {
		return replyDAO.selectReplyListPage(session, bno, cri);
	}

	@Override
	public int countReply(int bno) throws SQLException {
		return replyDAO.countReply(session, bno);
	}

}
