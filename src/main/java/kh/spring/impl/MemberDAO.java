package kh.spring.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import kh.spring.dto.MemberDTO;
import kh.spring.interfaces.IMemberDAO;

@Component
public class MemberDAO implements IMemberDAO {
	
	@Autowired
	private JdbcTemplate template;
	
	@Override
	public int insertMember(MemberDTO dto) {
		String sql = "insert into memberdb values(member_seq.nextval,?,?,?)";
		return template.update(sql,dto.getId(),dto.getPw(),dto.getEmail());
	}

}
