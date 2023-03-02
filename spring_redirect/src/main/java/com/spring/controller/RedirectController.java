package com.spring.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class RedirectController {
	
	@GetMapping("/send")
	public String send(RedirectAttributes rttr,HttpServletRequest request) throws Exception{
		String url = "redirect:/receive";
		
		//request.setAttribute("message", "네~~ 반갑습니다.");
		rttr.addFlashAttribute("message","네~~ 반갑습니다.");
		rttr.addAttribute("message","안녕하세요.!!!!!");
		
		return url; 
	}
	
	@GetMapping("/receive")
	public void receive() {}
}
