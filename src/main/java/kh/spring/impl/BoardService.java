package kh.spring.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import kh.spring.dto.BoardDTO;
import kh.spring.dto.CommentDTO;
import kh.spring.interfaces.IBoardDAO;
import kh.spring.interfaces.IBoardService;

@Component
public class BoardService implements IBoardService {
	
	@Autowired
	private IBoardDAO dao;

	@Override
	public List<BoardDTO> getBoardData(Map<String, String> condition) {
		return dao.getBoardData(condition);
	}

	@Override
	public List<BoardDTO> getSearchData(Map<String, String> condition) {
		return dao.getSearchData(condition);
	}

	@Override
	public String getPageNavi(Map<String, String> condition) {
		return dao.getPageNavi(condition);
	}

	@Override
	public int insertArticle(BoardDTO dto) {
		return dao.insertArticle(dto);
	}

	@Override
	public BoardDTO getArticle(int seq) {
		return dao.getArticle(seq);
	}

	@Override
	public int deleteArticle(int seq) {
		return dao.deleteArticle(seq);
	}

	@Override
	public int editArticle(BoardDTO dto) {
		return dao.editArticle(dto);
	}

	@Override
	public List<CommentDTO> getArticleComment(int seq) {
		return dao.getArticleComment(seq);
	}

	@Override
	public int insertComment(CommentDTO dto) {
		return dao.insertComment(dto);
	}

	@Override
	public int deleteComment(int comseq) {
		return dao.deleteComment(comseq);
	}

	@Override
	public int updateViewCount(int seq) {
		return dao.updateViewCount(seq);
	}

}
