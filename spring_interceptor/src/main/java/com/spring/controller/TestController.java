package com.spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class TestController {
	
	@GetMapping("/target")
	public String test(@RequestParam(defaultValue="0")int num,Model model)throws Exception{
		String url = "/test/main";
		
		if(num==1) {
			throw new Exception("from TestController");
		}
		
		model.addAttribute("message","To the victory!");
		
		return url;
	}
}





