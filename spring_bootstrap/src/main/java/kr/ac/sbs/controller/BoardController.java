package kr.ac.sbs.controller;

import java.sql.SQLException;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.jsp.command.SearchCriteria;
import com.jsp.dto.BoardVO;
import com.jsp.service.BoardService;

@Controller
@RequestMapping("/board")
public class BoardController {
	
	@Resource(name="boardService")
	private BoardService service;
	
	@RequestMapping("/main")
	public void main()throws Exception{}
	
	@GetMapping("/list")
	public ModelAndView list(SearchCriteria cri, ModelAndView mnv)throws SQLException{
		String url="/board/list";		
		
		Map<String,Object> dataMap = service.getBoardList(cri);
		
		mnv.addObject("dataMap",dataMap);
		mnv.setViewName(url);
		
		return mnv;
	}
	
	@GetMapping("/registForm")
	public String registForm(){
		String url="board/regist";		
		return url;
	}
	
	@PostMapping("/regist")
	public String regist(BoardVO board,HttpServletRequest request,RedirectAttributes rttr)throws Exception{
		String url="redirect:/board/list.do";	
		
		String XSStitle = (String)request.getAttribute("XSStitle");
		if(XSStitle !=null) board.setTitle(XSStitle);
		
		service.regist(board);
		
		rttr.addFlashAttribute("from","regist");
		
		return url;
	}
	

	@GetMapping("/detail")
	public ModelAndView detail(int bno,String from, ModelAndView mnv )throws SQLException{
		String url="/board/detail";		
		
		BoardVO board =null;
		if(from!=null && from.equals("list")) {
			board=service.getBoard(bno);
			url="redirect:/board/detail.do?bno="+bno;
		}else {
			board=service.getBoardForModify(bno);
		}
					
		mnv.addObject("board",board);		
		mnv.setViewName(url);
		
		return mnv;
	}
	
	@GetMapping("/modifyForm")
	public ModelAndView modifyForm(int bno,ModelAndView mnv)throws SQLException{
		String url="/board/modify";
		
		BoardVO board = service.getBoardForModify(bno);
		
		mnv.addObject("board",board);		
		mnv.setViewName(url);
		
		return mnv;
	}
	
	@PostMapping("/modify")
	public String modifyPost(BoardVO board,HttpServletRequest request, RedirectAttributes rttr) throws Exception{
		
		String url = "redirect:/board/detail.do";
				
		String XSStitle = (String)request.getAttribute("XSStitle");
		if(XSStitle !=null) board.setTitle(XSStitle);
		
		service.modify(board);
		
		rttr.addFlashAttribute("from","modify");
		rttr.addAttribute("bno",board.getBno());
		
		return url;
	}
	
	@PostMapping("/remove")
	public String remove(int bno,RedirectAttributes rttr) throws Exception{
		String url = "redirect:/board/detail";
		service.remove(bno);		
		
		rttr.addAttribute("bno",bno);
		rttr.addFlashAttribute("from","remove");
		return url;		
	}
}








