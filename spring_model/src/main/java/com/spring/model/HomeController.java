package com.spring.model;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {
	
	@GetMapping("/model/attribute")
	public void addAttribute(Model model) {
		
		Map<String,String> dataMap = new HashMap<String,String>();
		dataMap.put("name1", "mimi1");
		dataMap.put("name2", "mimi2");
		dataMap.put("name3", "mimi3");
		
		model.addAttribute("dataMap",dataMap);
		model.addAllAttributes(dataMap);
		
		model.addAttribute("message","good Day!!");
	}
	
	@GetMapping("/model/view")
	public ModelAndView modelnView(ModelAndView mnv) {
		
		
		mnv.addObject("message","Cheer up!!");
		//mnv.setViewName("/model/view");
		
		return mnv;
	}
}





