package kr.ac.sbs.dao;

import java.sql.SQLException;

import com.jsp.dto.BoardVO;
import com.jsp.dto.NoticeVO;
import com.jsp.dto.PdsVO;

public interface SchedulerDAO {
	
	NoticeVO selectNoticeByImage(String imageFile) throws SQLException;
	
	PdsVO selectPdsByImage(String imageFile) throws SQLException;
	
	BoardVO selectBoardByImage ( String imageFile) throws SQLException;
}
