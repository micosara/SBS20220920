package com.jsp.service;

import java.sql.SQLException;
import java.util.Map;

import com.jsp.command.SearchCriteria;
import com.jsp.dto.ReplyVO;

public interface ReplyService {
	// 리스트보기
	Map<String, Object> getReplyList(int bno, SearchCriteria cri) throws SQLException;

	// 리스트 개수
	int getReplyListCount(int bno) throws SQLException;

	// 등록
	void registReply(ReplyVO reply) throws SQLException;

	// 수정
	void modifyReply(ReplyVO reply) throws SQLException;

	// 삭제
	void removeReply(int rno) throws SQLException;
}
