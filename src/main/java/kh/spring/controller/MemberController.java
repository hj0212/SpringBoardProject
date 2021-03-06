package kh.spring.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import kh.spring.dto.MemberDTO;
import kh.spring.impl.MemberService;
import kh.spring.interfaces.IMemberService;

@Controller
public class MemberController {

	@Autowired
	IMemberService mservice;

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
	public ModelAndView toLoginProc(String id, String pw, HttpSession session) {
		List<MemberDTO> result = mservice.loginMember(id, pw);
		MemberDTO dto = result.get(0);
		session.setAttribute("seq", dto.getSeq());
		session.setAttribute("id", dto.getId());
		session.setAttribute("pw", dto.getPw());
		session.setAttribute("email", dto.getEmail());

		ModelAndView mav = new ModelAndView();		
		mav.addObject("loginresult",result);	
		mav.setViewName("loginProc.jsp");
		return mav;
	}



	@RequestMapping(value="/idCheck.me", method=RequestMethod.GET)
	@ResponseBody
	public void toIdCheck(HttpServletRequest request, HttpServletResponse resp) throws IOException{
		int result=1;
		String id= request.getParameter("id");
		/*System.out.println(id);*/
		List<MemberDTO> list =mservice.idCheck(id);
		/*System.out.println(list.size());*/
		if(list.isEmpty()) {
			result=0;
		}
		resp.getWriter().append(String.valueOf(result));
		/*ModelAndView mav = new ModelAndView();
		mav.addObject("idCheckresult",result);*/
	}
}
