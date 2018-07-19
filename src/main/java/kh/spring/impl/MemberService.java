package kh.spring.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import kh.spring.dto.MemberDTO;
import kh.spring.interfaces.IMemberService;

@Component
public class MemberService implements IMemberService{

	@Autowired
	private MemberDAO mdao;
	
	@Override
	public int insertMember(MemberDTO dto) {		
		return mdao.insertMember(dto);
	}

}
