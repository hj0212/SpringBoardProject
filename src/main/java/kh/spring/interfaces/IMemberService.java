package kh.spring.interfaces;

import java.util.List;

import kh.spring.dto.MemberDTO;

public interface IMemberService {
	
	public int insertMember(MemberDTO dto);
	
	public List<MemberDTO> loginMember(String id, String pw);
	
	public MemberDTO idCheck(String id);
}
