package kh.spring.controller;

import javax.servlet.http.HttpSession;

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
		
		return null;
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
	
	@RequestMapping("/toArticle")
	public ModelAndView toArticle(int seq) {
		BoardDTO result = service.getArticle(seq);
		ModelAndView mav = new ModelAndView();
		mav.addObject("result",result);
		mav.setViewName("article.jsp");
		return mav;
	}

}
