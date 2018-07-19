package kh.spring.interfaces;

import java.util.List;

import kh.spring.dto.BoardDTO;

public interface IBoardService {
	public List<BoardDTO> getBoardData();
	public List<BoardDTO> getSearchData(int startNum, int endNum, String keyword);
	public String getPageNavi(int currentPage, String searchTerm);
	public int insertArticle(String title,String writer, String contents,String ip);
	public BoardDTO getArticle(int seq);
	public int deleteArticle(int seq);
}
