package kh.spring.interfaces;

import java.util.List;

import kh.spring.dto.BoardDTO;
import kh.spring.dto.CommentDTO;

public interface IBoardDAO {
	public List<BoardDTO> getBoardData(int startNum, int endNum);
	public List<BoardDTO> getSearchData(int startNum, int endNum, String keyword);
	public String getPageNavi(int currentPage, String searchTerm);
	public int insertArticle(String title,String writer, String contents,String ip);
	public BoardDTO getArticle(int seq);
	public int deleteArticle(int seq);
	public int editArticle(String title, String contents, String ip, int seq);	
	public List<CommentDTO> getArticleComment(int seq);
	public int insertComment(CommentDTO dto);
}
