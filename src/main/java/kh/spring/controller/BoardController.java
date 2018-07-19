package kh.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import kh.spring.interfaces.IBoardService;

@Controller
public class BoardController {
	
	@Autowired
	private IBoardService service;
	
	@RequestMapping("/boardlist.bo")
	public ModelAndView goBoardList() {
		
		return null;
	}
}
