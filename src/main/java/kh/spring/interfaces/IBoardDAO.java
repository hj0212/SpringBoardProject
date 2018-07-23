package kh.spring.interfaces;

import java.util.List;
import java.util.Map;

import kh.spring.dto.BoardDTO;
import kh.spring.dto.CommentDTO;

public interface IBoardDAO {
	public List<BoardDTO> getBoardData(Map<String, String> condition);
	public List<BoardDTO> getSearchData(Map<String, String> condition);
	public String getPageNavi(Map<String, String> condition);
	public int insertArticle(BoardDTO dto);
	public BoardDTO getArticle(int seq);
	public int deleteArticle(int seq);
	public int editArticle(BoardDTO dto);
	public List<CommentDTO> getArticleComment(int seq);
	public int insertComment(CommentDTO dto);
	public int deleteComment(int comseq);
	public int updateViewCount(int seq);
}
