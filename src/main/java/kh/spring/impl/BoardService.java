package kh.spring.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import kh.spring.dto.BoardDTO;
import kh.spring.interfaces.IBoardDAO;
import kh.spring.interfaces.IBoardService;

public class BoardService implements IBoardService {
	
	@Autowired
	private IBoardDAO dao;

	@Override
	public List<BoardDTO> getBoardData() {
		// TODO Auto-generated method stub
		return null;
	}

}
