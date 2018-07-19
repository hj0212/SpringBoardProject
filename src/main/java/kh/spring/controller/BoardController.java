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
	public ModelAndView goBoardList(String currentPage, String searchTerm) {
		List<BoardDTO> list = service.getBoardData();
		int currentPagenum = 0;

		if(currentPage == null) {
			currentPagenum = 1;
		} else {
			currentPagenum = Integer.parseInt(currentPage);
		}
		String pageNavi = service.getPageNavi(currentPagenum, searchTerm);
		ModelAndView mav = new ModelAndView();
		mav.addObject("list", list);
		mav.addObject("pageNavi", pageNavi);
		mav.setViewName("boardList.jsp");
		return mav;
	}

	@RequestMapping("/search.bo")
	public ModelAndView getSearchData(String currentPage, String keyword) {
		int currentPagenum = 0;

		if(currentPage == null) {
			currentPagenum = 1;
		} else {
			currentPagenum = Integer.parseInt(currentPage);
		}

		List<BoardDTO> list = service.getSearchData(currentPagenum*10-9, currentPagenum*10, keyword);
		String pageNavi = service.getPageNavi(currentPagenum, keyword);
		ModelAndView mav = new ModelAndView();
		mav.addObject("list", list);
		mav.addObject("pageNavi", pageNavi);
		mav.setViewName("boardList.jsp");
		return mav;
	}
}
