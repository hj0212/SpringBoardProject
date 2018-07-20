package kh.spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class FrontController {

	@RequestMapping("toLogin.do")
	public String toLogin(){
		return "redirect:login.jsp";
	}
}
