package kh.spring.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
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
	public ModelAndView writeArticle(HttpServletRequest req, HttpSession session,String title,String contents) {
		String writer = (String) session.getAttribute("id");
		String ip = req.getRemoteAddr();
		System.out.println("writer/ip 넣지않음");
		System.out.println("BoardController writeArticleProc.bo :"+writer+":"+ip);
		int result=service.insertArticle(new BoardDTO(0,title, writer , contents, "",0, ip));
		ModelAndView mav = new ModelAndView();
		mav.addObject("result",result);
		mav.setViewName("writeArticleProcView.jsp");
		return mav;
	}
	
	@RequestMapping("/toArticle.bo")
	public ModelAndView toArticle(HttpSession session, int seq) {
		BoardDTO result = service.getArticle(seq);
		String loginId = (String) session.getAttribute("id");
		System.out.println("toArticle.bo - loginId : "+loginId);
		ModelAndView mav = new ModelAndView();
		mav.addObject("result",result);
		mav.addObject("loginId", loginId);
		mav.setViewName("article.jsp");
		return mav;
	}
	
	@RequestMapping("/toDeleteArticleProc.bo")
	public ModelAndView deleteArticle(int seq) {
		System.out.println("toDeleteArticleProc.bo : "+seq);
		int result = service.deleteArticle(seq);
		ModelAndView mav = new ModelAndView();
		mav.addObject("result",result);
		mav.setViewName("deleteProcView.jsp");
		return mav;
	}
	
	@RequestMapping("/toEditArticle.bo")
	public ModelAndView toEditArticle(int seq) {
		System.out.println("toEditArticle.bo : "+seq);
		BoardDTO result = service.getArticle(seq);
		ModelAndView mav = new ModelAndView();
		mav.addObject("result",result);
		mav.setViewName("editArticle.jsp");
		return mav;
	}
	
	@RequestMapping("/toEditArticleProc.bo")
	public ModelAndView editArticle(int seq, String title, String contents,HttpServletRequest req) {
		String ip=req.getRemoteAddr();
		System.out.println("toEditArticleProc.bo-ip:"+ip);
		int result = service.editArticle(new BoardDTO (seq,title,"",contents,"",0,ip));		
		ModelAndView mav = new ModelAndView();
		mav.addObject("result",result);
		mav.addObject("seq",seq);
		mav.setViewName("editArticleProcView.jsp");
		return mav;
	}
}
