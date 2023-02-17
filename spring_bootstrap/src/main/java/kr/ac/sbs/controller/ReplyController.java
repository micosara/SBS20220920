package kr.ac.sbs.controller;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jsp.command.PageMaker;
import com.jsp.command.SearchCriteria;
import com.jsp.dto.ReplyVO;
import com.jsp.service.ReplyService;

@RestController
@RequestMapping("/reply")
public class ReplyController {
	
	@Resource(name="replyService")
	private ReplyService service;
	
	@GetMapping("/list")
	public ResponseEntity<Map<String,Object>> list(int bno, 
							@RequestParam(defaultValue = "1")int page)throws Exception{
		ResponseEntity<Map<String, Object>> entity = null;
		
		SearchCriteria cri = new SearchCriteria();
		cri.setPage(page);
		
		Map<String, Object> dataMap = service.getReplyList(bno, cri);
		entity = new ResponseEntity<Map<String, Object>>(dataMap, HttpStatus.OK);
		
		return entity;
	}
	
	@PostMapping("/regist")
	public ResponseEntity<String> register(@RequestBody ReplyVO reply) throws Exception {
		ResponseEntity<String> entity = null;

		service.registReply(reply);

		SearchCriteria cri = new SearchCriteria();

		PageMaker pageMaker = new PageMaker();
		pageMaker.setCri(cri);
		pageMaker.setTotalCount(service.getReplyListCount(reply.getBno()));
		
		int realEndPage = pageMaker.getRealEndPage();

		entity = new ResponseEntity<String>(realEndPage + "", HttpStatus.OK);

		return entity;
	}
	
	@PostMapping("/modify")
	public ResponseEntity<String> modify(@RequestBody ReplyVO reply) throws Exception {
		ResponseEntity<String> entity = null;

		service.modifyReply(reply);
		entity = new ResponseEntity<String>(HttpStatus.OK);

		return entity;
	}
	
	@GetMapping(value = "/remove")
	public ResponseEntity<String> remove(int rno,int bno,int page) throws Exception {
		ResponseEntity<String> entity = null;

		service.removeReply(rno);

		SearchCriteria cri = new SearchCriteria();

		PageMaker pageMaker = new PageMaker();
		pageMaker.setCri(cri);
		pageMaker.setTotalCount(service.getReplyListCount(bno));
		
		int realEndPage = pageMaker.getRealEndPage();
		if (page > realEndPage) {
			page = realEndPage;
		}

		entity = new ResponseEntity<String>("" + page, HttpStatus.OK);

		return entity;
	}
}


















