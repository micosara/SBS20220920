package kr.ac.sbs.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.jsp.service.MemberService;

import kr.ac.sbs.exception.InvalidPasswordException;
import kr.ac.sbs.exception.NotFoundIdException;

@Controller
public class CommonController {
	
	@Resource(name="memberService")
	private MemberService memberService;
	
	@Resource(name="loginMemberService")
	private kr.ac.sbs.service.MemberService loginMemberService;
	
	@GetMapping("/main")
	public String main() {
		String url = "/home";
		return url;
	}
	
	@GetMapping("/common/loginForm")
	public void loginForm() {}
	
	
	@PostMapping("/common/login")
	public String login(String id, String pwd, HttpSession session,RedirectAttributes rttr) throws Exception {
		String url = "redirect:/index.do";
		try {
			loginMemberService.login(id, pwd);
			session.setAttribute("loginUser", loginMemberService.getMember(id));
		} catch (NotFoundIdException | InvalidPasswordException e) {
			url = "redirect:/common/loginForm.do";
			
			rttr.addFlashAttribute("message",e.getMessage());
			rttr.addFlashAttribute("id", id);
		} 
		return url;
	}
	
	@GetMapping("/common/logout")
	public String logout(HttpSession session) {
		String url = "redirect:/";
		session.invalidate();
		return url;
	}
}



