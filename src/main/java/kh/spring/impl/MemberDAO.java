package kh.spring.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
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

	@Override
	public List<MemberDTO> loginMember(String id, String pw) {
		String sql = "select * from memberdb where id= ? and pw=?";
		Object[] param = {id, pw};
		return template.query(sql, param,new RowMapper<MemberDTO>() {		
			public MemberDTO mapRow(ResultSet rs, int rowNum) throws SQLException{
				MemberDTO tmp = new MemberDTO();
				tmp.setSeq(rs.getInt("seq"));
				tmp.setId(rs.getString("id"));
				tmp.setPw(rs.getString("pw"));
				tmp.setEmail(rs.getString("email"));
				return tmp;				
			}
		});
	}

	@Override
	public MemberDTO idCheck(String id) {
		String sql="select * from memberdb where id=?";
		Object[] param = {id};
		return template.queryForObject(sql, param, new RowMapper<MemberDTO>() {
			public MemberDTO mapRow(ResultSet rs, int rowNum)throws SQLException{
				MemberDTO tmp = new MemberDTO();
				tmp.setSeq(rs.getInt("seq"));
				tmp.setId(rs.getString("id"));
				tmp.setPw(rs.getString("pw"));
				tmp.setEmail(rs.getString("email"));
				return tmp;
			}
		});
		
	}
			
			
			
	
	

}
