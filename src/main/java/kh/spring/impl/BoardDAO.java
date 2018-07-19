package kh.spring.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import kh.spring.dto.BoardDTO;
import kh.spring.interfaces.IBoardDAO;

@Component
public class BoardDAO implements IBoardDAO {
	
	@Autowired
	private JdbcTemplate template;

	@Override
	public List<BoardDTO> getBoardData() {
		String sql = "select * from boarddb";
		return template.query(sql, new RowMapper<BoardDTO>() {

			@Override
			public BoardDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
				BoardDTO dto = new BoardDTO();
				dto.setSeq(rs.getInt("seq"));
				dto.setTitle(rs.getString("title"));
				dto.setWriter(rs.getString("writer"));
				dto.setContents(rs.getString("contents"));
				dto.setWritedate(rs.getString("writedate"));
				dto.setViewcount(rs.getInt("viewcount"));
				dto.setIp(rs.getString("ip"));
				return dto;
			}
			
		});
	}

}
