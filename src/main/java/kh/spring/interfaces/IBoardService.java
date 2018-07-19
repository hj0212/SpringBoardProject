package kh.spring.interfaces;

import java.util.List;

import kh.spring.dto.BoardDTO;

public interface IBoardService {
	public List<BoardDTO> getBoardData(int startNum, int endNum);
	public List<BoardDTO> getSearchData(int startNum, int endNum, String keyword);
	public String getPageNavi(int currentPage, String searchTerm);
}
