package kh.spring.interfaces;

import java.util.List;

import kh.spring.dto.BoardDTO;

public interface IBoardDAO {
	public List<BoardDTO> getBoardData();
	public int insertArticle(String title,String writer, String contents,String ip);
	public BoardDTO getArticle(int seq);
}
