package com.spring.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.jsp.dto.MenuVO;

public class MenuDAOImpl implements MenuDAO{

	private SqlSession session;
	public void setSession(SqlSession session) {
		this.session = session;
	}

	private com.jsp.dao.MenuDAO menuDAO;	
	public void setMenuDAO(com.jsp.dao.MenuDAO menuDAO) {
		this.menuDAO = menuDAO;
	}

	@Override
	public List<MenuVO> selectMainMenu() throws SQLException {		
		return menuDAO.selectMainMenu(session);
	}

	@Override
	public MenuVO selectMenuByMcode(String mCode) throws SQLException {		
		return menuDAO.selectMenuByMcode(session, mCode);
	}

	@Override
	public MenuVO selectMenuByMname(String mName) throws SQLException {		
		return menuDAO.selectMenuByMname(session, mName);
	}

	@Override
	public List<MenuVO> selectSubMenu(String mCode) throws SQLException {	
		return menuDAO.selectSubMenu(session, mCode);
	}

}
