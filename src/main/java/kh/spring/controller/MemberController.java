package kh.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import kh.spring.dto.MemberDTO;
import kh.spring.impl.MemberService;

@Controller
public class MemberController {
	
	@Autowired
	MemberService mservice;
	
	@RequestMapping("/join.me")
	public String toJoin() {
		return "redirect:join.jsp";
	}
	@RequestMapping("/joinProc.me")
	public ModelAndView toJoinProc(MemberDTO dto) {
		int result =mservice.insertMember(dto);
		ModelAndView mav = new ModelAndView();
		mav.addObject("joinresult",result);
		mav.setViewName("joinProc.jsp");
		return mav;
	} 
	
}
