/**
 * 
 */
package com.alok91340.epam.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest;

/**
 * @author alok91340
 *
 */
@Controller
public class HomeController {
	
	@RequestMapping("/welcome")
	public String welcomePage() {
		return "welcome";
	}
	@RequestMapping("/login")
	public String loginPage() {
		return "login";
	}
	@RequestMapping("/register")
	public String registerationPage() {
		return "register";
	}
	
	
	
//	@RequestMapping(path = "/formprocess", method=RequestMethod.POST)
//	public String process(HttpServletRequest request) {
//		
//		String name= (String) request.getParameter("username");
//		System.out.print(name);
//		return "index";
//	}
}
