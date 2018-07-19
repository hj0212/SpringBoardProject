package kh.spring.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import kh.spring.dto.BoardDTO;
import kh.spring.interfaces.IBoardDAO;
import kh.spring.interfaces.IBoardService;

@Component
public class BoardService implements IBoardService {
	
	@Autowired
	private IBoardDAO dao;

	@Override
	public List<BoardDTO> getBoardData() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int insertArticle(String title, String writer, String contents, String ip) {
		return dao.insertArticle(title, writer, contents, ip);
	}

	@Override
	public BoardDTO getArticle(int seq) {
		return dao.getArticle(seq);
	}

}
