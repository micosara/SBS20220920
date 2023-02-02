package com.jsp.service;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.jsp.command.PageMaker;
import com.jsp.command.SearchCriteria;
import com.jsp.dao.ReplyDAO;
import com.jsp.dto.ReplyVO;

public class ReplyServiceImpl implements ReplyService{
	private SqlSessionFactory sqlSessionFactory;
	public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
		this.sqlSessionFactory = sqlSessionFactory;
	}
	private ReplyDAO replyDAO;
	public void setReplyDAO(ReplyDAO replyDAO) {
		this.replyDAO = replyDAO;
	}
	
	@Override
	public Map<String, Object> getReplyList(int bno, SearchCriteria cri) throws SQLException {
		SqlSession session = sqlSessionFactory.openSession();
		
		Map<String, Object> dataMap = new HashMap<String, Object>();
		
		try {
			List<ReplyVO> replyList = replyDAO.selectReplyListPage(session, bno, cri);			
			
			int count = replyDAO.countReply(session,bno);
			
			PageMaker pageMaker = new PageMaker();
			pageMaker.setCri(cri);
			pageMaker.setTotalCount(count);
	
			dataMap.put("replyList", replyList);
			dataMap.put("pageMaker", pageMaker);
			
			return dataMap;
			
		}finally{
			session.close();
		}
		
	}

	@Override
	public int getReplyListCount(int bno) throws SQLException {
		SqlSession session = sqlSessionFactory.openSession();
		try {
			int count = replyDAO.countReply(session, bno);
			return count;
		} finally {
			session.close();
		}
	}

	@Override
	public void registReply(ReplyVO reply) throws SQLException {
		SqlSession session = sqlSessionFactory.openSession();
		try {
			int rno = replyDAO.selectReplySeqNextValue(session);
			reply.setRno(rno);

			replyDAO.insertReply(session,reply);
		} finally {
			session.close();
		}
		

	}

	@Override
	public void modifyReply(ReplyVO reply) throws SQLException {
		SqlSession session = sqlSessionFactory.openSession();
		try {

			replyDAO.updateReply(session,reply);
		} finally {
			session.close();
		}		
	}

	@Override
	public void removeReply(int rno) throws SQLException {
		SqlSession session = sqlSessionFactory.openSession();
		try {

			replyDAO.deleteReply(session,rno);
		} finally {
			session.close();
		}

	}
}
