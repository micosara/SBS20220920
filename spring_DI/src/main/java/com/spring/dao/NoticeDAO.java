package com.spring.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.jsp.command.SearchCriteria;
import com.jsp.dto.NoticeVO;

public interface NoticeDAO{
	
	List<NoticeVO> selectSearchNoticeList(SearchCriteria cri) throws SQLException;
	
	int selectSearchNoticeListCount(SearchCriteria cri) throws SQLException;
	
	NoticeVO selectNoticeByNno(  int nno) throws SQLException;	

	void increaseViewCount(  int nno) throws SQLException;
	
	int selectNoticeSequenceNextValue() throws SQLException;
	
	void insertNotice(  NoticeVO Notice) throws SQLException;
	
	void updateNotice(  NoticeVO Notice) throws SQLException;
	
	void deleteNotice(  int nno) throws SQLException;
}
