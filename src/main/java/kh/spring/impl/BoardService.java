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
		return dao.getBoardData();
	}

	@Override
	public List<BoardDTO> getSearchData(int startNum, int endNum, String keyword) {
		return dao.getSearchData(startNum, endNum, keyword);
	}

	@Override
	public String getPageNavi(int currentPage, String searchTerm) {
		return dao.getPageNavi(currentPage, searchTerm);
	}

}
