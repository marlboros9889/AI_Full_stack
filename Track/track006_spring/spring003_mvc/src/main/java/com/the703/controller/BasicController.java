package com.the703.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
//<mvc:annotation-driven /> 
import org.springframework.web.bind.annotation.RequestMapping;
@Controller
public class BasicController {

	@RequestMapping("/basic.do")
	public String basic(Model model) {
		model.addAttribute("result", "Hello");
		return "basic";
	}
}
