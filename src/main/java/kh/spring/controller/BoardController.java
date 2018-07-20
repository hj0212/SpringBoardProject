package kh.spring.controller;

import javax.servlet.http.HttpSession;

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
	
	@RequestMapping("/toWriteArticle.bo")
	public String toWriteArticle() {
		return "redirect:writeArticle.jsp";
	}
	
	@RequestMapping("/toWriteArticleProc.bo")
	public ModelAndView writeArticle(HttpSession session,String title,String contents) {
		String writer = (String) session.getAttribute("id");
		String ip = (String) session.getAttribute("ip");
		System.out.println("writer/ip 넣지않음");
		System.out.println("BoardController writeArticleProc.bo :"+writer+":"+ip);
		int result=service.insertArticle(title, "test:writer", contents, "test:IP");
		ModelAndView mav = new ModelAndView();
		mav.addObject("result",result);
		mav.setViewName("writeArticleProcView.jsp");
		return mav;
	}
	
	@RequestMapping("/toArticle.bo")
	public ModelAndView toArticle(int seq) {
		seq=1;
		BoardDTO result = service.getArticle(seq);
		ModelAndView mav = new ModelAndView();
		mav.addObject("result",result);
		mav.setViewName("article.jsp");
		return mav;
	}
	
	public String toDeleteArticle() {
		return "redirect:deleteArticle.jsp";		
	}

	public ModelAndView deleteArticle(int seq) {
		int result = service.deleteArticle(seq);
		ModelAndView mav = new ModelAndView();
		mav.addObject("result",result);
		mav.setViewName("deleteProcView.jsp");
		return mav;
	}
}
