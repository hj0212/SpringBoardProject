package kh.spring.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
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
	
	@RequestMapping("/loginProc.me")
	public ModelAndView toLoginProc(String id, String pw) {
		List<MemberDTO> result = mservice.loginMember(id, pw);
		ModelAndView mav = new ModelAndView();
		mav.addObject("loginresult",result);
		mav.setViewName("loginProc.jsp");
		return mav;
	}
	
	@ResponseBody
	@RequestMapping("/idCheck.me")
	public int toIdCheck(@RequestBody String id) {
		System.out.println(id);
		int result=0;
		MemberDTO dto =mservice.idCheck(id);
		if(dto.getId() != null) {
			result=1;
			}
		/*ModelAndView mav = new ModelAndView();
		mav.addObject("idCheckresult",result);*/
		return result;
	}
}
