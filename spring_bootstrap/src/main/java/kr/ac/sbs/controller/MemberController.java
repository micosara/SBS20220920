package kr.ac.sbs.controller;

import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.jsp.command.SearchCriteria;
import com.jsp.dto.MemberVO;
import com.jsp.service.MemberService;

import kr.ac.sbs.command.MemberRegistCommand;


@Controller
@RequestMapping("/member")
public class MemberController {

	@Resource(name = "memberService")
	private MemberService memberService;

	@GetMapping("/main")
	public void main() {
	}

	@GetMapping("/list")
	public void list(SearchCriteria cri, HttpServletRequest request) throws Exception {
		Map<String, Object> dataMap = memberService.getMemberListForPage(cri);
		request.setAttribute("dataMap", dataMap);
	}
	
	@GetMapping("/registForm")
	public String registForm() {
		String url ="/member/regist";
		return url;
	}
	
	@PostMapping("/regist")
	public String regist(MemberRegistCommand memberReq) throws Exception {
		String url = "/member/regist_success";

		MemberVO member = memberReq.toMemberVO();
		memberService.regist(member);

		return url;
	}


	@Resource(name = "picturePath")
	private String picturePath;
	
	@PostMapping(value = "/picture", produces = "text/plain;charset=utf-8")
	@ResponseBody
	public String pictureUpload(@RequestParam("pictureFile")MultipartFile multi,
								 String oldPicture)throws Exception {
			
		
		
		return null;
	}
}


















