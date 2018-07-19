package kh.spring.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import kh.spring.dto.BoardDTO;
import kh.spring.interfaces.IBoardDAO;

@Component
public class BoardDAO implements IBoardDAO {
	
	@Autowired
	private JdbcTemplate template;

	@Override
	public List<BoardDTO> getBoardData() {
		// TODO Auto-generated method stub
		return null;
	}

}
