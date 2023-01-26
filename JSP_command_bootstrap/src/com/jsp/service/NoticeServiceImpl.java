package com.jsp.service;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.jsp.command.PageMaker;
import com.jsp.command.SearchCriteria;
import com.jsp.dao.NoticeDAO;
import com.jsp.dto.NoticeVO;

public class NoticeServiceImpl implements NoticeService{

	private SqlSessionFactory sqlSessionFactory;
	public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
		this.sqlSessionFactory = sqlSessionFactory;
	}

	private NoticeDAO noticeDAO;
	public void setNoticeDAO(NoticeDAO noticeDAO) {
		this.noticeDAO = noticeDAO;
	}
	
	@Override
	public Map<String, Object> getNoticeList(SearchCriteria cri) throws SQLException {

		SqlSession session = sqlSessionFactory.openSession();
		try {
			Map<String, Object> dataMap = new HashMap<String, Object>();

			// 현재 page 번호에 맞는 리스트를 perPageNum 개수 만큼 가져오기.
			List<NoticeVO> noticeList = noticeDAO.selectSearchNoticeList(session, cri);

			// 전체 board 개수
			int totalCount = noticeDAO.selectSearchNoticeListCount(session, cri);

			// PageMaker 생성.
			PageMaker pageMaker = new PageMaker();
			pageMaker.setCri(cri);
			pageMaker.setTotalCount(totalCount);

			dataMap.put("noticeList", noticeList);
			dataMap.put("pageMaker", pageMaker);

			return dataMap;
		} finally {
			session.close();
		}
	}

	@Override
	public NoticeVO getNotice(int nno) throws SQLException {
		SqlSession session = sqlSessionFactory.openSession();
		try {
			NoticeVO board = noticeDAO.selectNoticeByNno(session, nno);
			noticeDAO.increaseViewCount(session, nno);
			return board;
		} finally {
			session.close();
		}
	}

	@Override
	public NoticeVO getNoticeForModify(int nno) throws SQLException {
		SqlSession session = sqlSessionFactory.openSession();
		try {
			NoticeVO board = noticeDAO.selectNoticeByNno(session, nno);
			return board;
		} finally {
			session.close();
		}
	}

	@Override
	public void regist(NoticeVO notice) throws SQLException {
		SqlSession session = sqlSessionFactory.openSession();
		try {

			int nno = noticeDAO.selectNoticeSequenceNextValue(session);
			notice.setNno(nno);
			noticeDAO.insertNotice(session, notice);
		} finally {
			session.close();
		}
	}
	@Override
	public void modify(NoticeVO notice) throws SQLException {
		SqlSession session = sqlSessionFactory.openSession();
		try {

			noticeDAO.updateNotice(session, notice);
		} finally {
			session.close();
		}
	}

	@Override
	public void remove(int nno) throws SQLException {
		SqlSession session = sqlSessionFactory.openSession();
		try {

			noticeDAO.deleteNotice(session, nno);
		} finally {
			session.close();
		}
	}
}
