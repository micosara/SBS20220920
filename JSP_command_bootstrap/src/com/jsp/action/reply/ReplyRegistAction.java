package com.jsp.action.reply;

import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.josephoconnell.html.HTMLInputFilter;
import com.jsp.action.Action;
import com.jsp.command.PageMaker;
import com.jsp.command.SearchCriteria;
import com.jsp.dto.ReplyVO;
import com.jsp.service.ReplyService;

public class ReplyRegistAction implements Action {

	private ReplyService replyService;

	public void setReplyService(ReplyService replyService) {
		this.replyService = replyService;
	}

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		String url=null;
		
		ObjectMapper mapper = new ObjectMapper();
		ReplyVO reply = mapper.readValue(request.getReader(), ReplyVO.class);

		String replytext = HTMLInputFilter.htmlSpecialChars(reply.getReplytext());
		reply.setReplytext(replytext);

		
		//DB
		try {
			replyService.registReply(reply);
			
		}catch(SQLException e) {
			e.printStackTrace();
			response.sendError(response.SC_INTERNAL_SERVER_ERROR);		
		}
		
		//realEndPage
		int realEndPage = 1;
				
		PageMaker pageMaker = new PageMaker();
		pageMaker.setCri(new SearchCriteria());
		pageMaker.setTotalCount(replyService.getReplyListCount(reply.getBno()));
		
		realEndPage = pageMaker.getRealEndPage();
		
		PrintWriter out = response.getWriter();
		out.print(realEndPage);
		
		out.close();
		
		return url;

	}

}
