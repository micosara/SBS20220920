package kr.ac.sbs.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.jsp.service.MemberService;

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
	
	@GetMapping(value = "/common/loginForm")
	public String loginForm(@RequestParam(defaultValue = "") String error, 
							HttpServletResponse response) {
		String url = "/common/loginForm";

		if (error.equals("-1")) {
			response.setStatus(302);
		}
		return url;

	}
	
	@GetMapping("/security/accessDenied")
	public void accessDenied() {}
	
	@GetMapping("/common/loginTimeOut")
	public String loginTimeOut(Model model)throws Exception {
		
		String url="/security/sessionOut";
		
		model.addAttribute("message","세션이 만료되었습니다.\\n다시 로그인 하세요!");
		return url;
	}
	
//	
//	@PostMapping("/common/login")
//	public String login(String id, String pwd, HttpSession session,RedirectAttributes rttr) throws Exception {
//		String url = "redirect:/index.do";
//		try {
//			loginMemberService.login(id, pwd);
//			session.setAttribute("loginUser", loginMemberService.getMember(id));
//		} catch (NotFoundIdException | InvalidPasswordException e) {
//			url = "redirect:/common/loginForm.do";
//			
//			rttr.addFlashAttribute("message",e.getMessage());
//			rttr.addFlashAttribute("id", id);
//		} 
//		return url;
//	}
	
//	@GetMapping("/common/logout")
//	public String logout(HttpSession session) {
//		String url = "redirect:/";
//		session.invalidate();
//		return url;
//	}
}



