package com.jsp.action.board;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jsp.action.Action;
import com.jsp.dto.BoardVO;
import com.jsp.service.BoardService;

public class BoardDetailAction implements Action{

	private BoardService boardService;
	public void setBoardService(BoardService boardService) {
		this.boardService = boardService;
	}	
	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String url = "/board/detail";
		
		int bno = Integer.parseInt(request.getParameter("bno"));
		String from = request.getParameter("from");
		
		BoardVO board;
		if(from!=null && from.equals("list")) {
			board=boardService.getBoard(bno);
			url="redirect:/board/detail.do?bno="+bno;
		}else {				
			board=boardService.getBoardForModify(bno);
		}

		request.setAttribute("board", board);
		
		return url;
	}

}







