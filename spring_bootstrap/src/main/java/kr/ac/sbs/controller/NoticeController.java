package kr.ac.sbs.controller;

import java.sql.SQLException;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.jsp.command.SearchCriteria;
import com.jsp.dto.NoticeVO;
import com.jsp.service.NoticeService;



@Controller
@RequestMapping("/notice")
public class NoticeController {

	@Resource(name="noticeService")
	private NoticeService noticeService;
	
	@GetMapping("/main")
	public void main() {}
	
	@GetMapping("/list")
	public ModelAndView list(SearchCriteria cri,ModelAndView mnv)throws Exception{
		
		String url="/notice/list";
		
		Map<String,Object> dataMap = noticeService.getNoticeList(cri);		
		mnv.addObject("dataMap",dataMap);
		mnv.setViewName(url);
		
		return mnv;
	}
	
	@GetMapping("/registForm")
	public String registForm(){
		String url = "notice/regist";
		return url;
	}

	@PostMapping("/regist")
	public String regist(NoticeVO notice,HttpServletRequest request, RedirectAttributes rttr) throws Exception{
		String url = "redirect:/notice/list";		
		
		String XSStitle = (String)request.getAttribute("XSStitle");
		if(XSStitle !=null) notice.setTitle(XSStitle);
		
		noticeService.regist(notice);	
		
		rttr.addFlashAttribute("from","regist");
				
		return url;
	}
	
	@GetMapping("/detail")
	public ModelAndView detail(int nno,@RequestParam(defaultValue="") String from,
							   ModelAndView mnv ) throws SQLException{
		String url="/notice/detail";
		
		NoticeVO notice = null;
		
		if(!from.equals("list")) {
			notice = noticeService.getNoticeForModify(nno);
		}else {
			notice = noticeService.getNotice(nno);
			url="redirect:/notice/detail.do?nno="+nno;
		}
		
		mnv.addObject("notice",notice);
		mnv.setViewName(url);
		
		return mnv;
	}
	
	@GetMapping("/modifyForm")
	public ModelAndView modifyForm(int nno,ModelAndView mnv) throws Exception{
		String url="/notice/modify";
		
		NoticeVO notice = noticeService.getNoticeForModify(nno);
		
		mnv.addObject("notice",notice);
		mnv.setViewName(url);
		
		return mnv;
		
	}
	
	@RequestMapping(value="/modify",method=RequestMethod.POST)
	public String modifyPost(NoticeVO notice,HttpServletRequest request,RedirectAttributes rttr)throws Exception{
		String url = "redirect:/notice/detail.do";
		
		String XSStitle = (String)request.getAttribute("XSStitle");
		if(XSStitle !=null) notice.setTitle(XSStitle);		
		
		noticeService.modify(notice);
		
		rttr.addAttribute("nno",notice.getNno());
		rttr.addFlashAttribute("from","modify");
		
		return url;
	}
	
	@PostMapping("/remove")
	public String remove(int nno,RedirectAttributes rttr) throws Exception{
		String url="redirect:/notice/detail.do";
			
		noticeService.remove(nno);		
		
		rttr.addFlashAttribute("from","remove");
		rttr.addAttribute("nno",nno);
		
		return url;
	}
	
}














