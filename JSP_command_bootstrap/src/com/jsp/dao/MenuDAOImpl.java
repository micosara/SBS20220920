package com.jsp.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.jsp.dto.MenuVO;

public class MenuDAOImpl implements MenuDAO{

	@Override
	public List<MenuVO> selectMainMenu(SqlSession session) throws SQLException {
		List<MenuVO> menuList = session.selectList("Menu-Mapper.selectMainMenu");
		return menuList;
	}

	@Override
	public List<MenuVO> selectSubMenu(SqlSession session, String mCode) throws SQLException {
		List<MenuVO> menuList = session.selectList("Menu-Mapper.selectSubMenu",mCode);
		return menuList;
	}

	@Override
	public MenuVO selectMenuByMcode(SqlSession session, String mCode) throws SQLException {
		MenuVO menu = session.selectOne("Menu-Mapper.selectMenuByMcode",mCode);
		return menu;
	}
	
	@Override
	public MenuVO selectMenuByMname(SqlSession session, String mName) throws SQLException {
		MenuVO menu = session.selectOne("Menu-Mapper.selectMenuByMname",mName);
		return menu;
	}
}
