package kh.spring.interfaces;

import java.util.List;

import kh.spring.dto.BoardDTO;
import kh.spring.dto.CommentDTO;

public interface IBoardService {
	public List<BoardDTO> getBoardData(int startNum, int endNum);
	public List<BoardDTO> getSearchData(int startNum, int endNum, String keyword);
	public String getPageNavi(int currentPage, String searchTerm);
	public int insertArticle(BoardDTO dto);
	public BoardDTO getArticle(int seq);
	public int deleteArticle(int seq);
	public int editArticle(BoardDTO dto);
	public List<CommentDTO> getArticleComment(int seq);
	public int insertComment(CommentDTO dto);
	public int deleteComment(int comseq);
	public int updateViewCount(int seq);
}
