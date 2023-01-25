package com.jsp.action.common;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jsp.action.Action;
import com.jsp.dto.MenuVO;
import com.jsp.service.MenuService;

public class IndexPageAction implements Action{
	
	private MenuService menuService;
	public void setMenuService(MenuService menuService) {
		this.menuService = menuService;
	}
	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String url = "/common/indexPage";
		
		String mCode = request.getParameter("mCode");
		if(mCode ==null) mCode="M000000";
		
		//GNB
		List<MenuVO> menuList = menuService.getMainMenuList();			
		//SUBMENU
		MenuVO menu = menuService.getMenuByMcode(mCode);
		
		request.setAttribute("menuList", menuList);
		request.setAttribute("menu", menu);
		
		return url;
	}

}
