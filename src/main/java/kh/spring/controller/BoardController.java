package kh.spring.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import kh.spring.dto.BoardDTO;
import kh.spring.interfaces.IBoardService;

@Controller
public class BoardController {
	
	@Autowired
	private IBoardService service;
	
	@RequestMapping("/boardlist.bo")
	public ModelAndView goBoardList() {
		List<BoardDTO> list = service.getBoardData();
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("list", list);
		mav.setViewName("boardList.jsp");
		return mav;
	}
}
