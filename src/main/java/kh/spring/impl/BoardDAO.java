package kh.spring.impl;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import kh.spring.dto.BoardDTO;
import kh.spring.dto.CommentDTO;
import kh.spring.interfaces.IBoardDAO;

@Component
public class BoardDAO implements IBoardDAO {

	@Autowired
	private SqlSessionTemplate template;

	@Override
	public List<BoardDTO> getBoardData(Map<String, String> condition) {
		return template.selectList("Board.getAllData", condition);
	}

	@Override
	public List<BoardDTO> getSearchData(Map<String, String> condition) {
		return template.selectList("Board.getSearchData", condition);
	}

	@Override
	public String getPageNavi(Map<String, String> condition) {
		String sql = null;
		int recordTotalCount = 0; // 전체 글(레코드)의 개수를 저장하는 변수
		recordTotalCount = (Integer) template.selectList("Board.getTotalCount", condition).get(0);

		int recordCountPerPage = 10;  // 한 페이지에 게시글이 몇개 보일건지
		int naviCountPerPage = 10;  // 한 페이지에서 네비게이터가 몇개씩 보일건지
		int pageTotalCount = 0;  // 전체가 몇페이지로 구성될것인지

		if(recordTotalCount % recordCountPerPage > 0 ) { // 정확히 10으로 나누어 떨어지지 않음
			pageTotalCount = recordTotalCount / recordCountPerPage + 1;
		} else {
			pageTotalCount = recordTotalCount / recordCountPerPage;
		}
		
		int currentPage = Integer.parseInt(condition.get("currentPage"));
		if(currentPage < 1) {	// 현재 페이지가 비정상인지 검증하는 코드
			currentPage = 1;
		} else if(currentPage > pageTotalCount) {
			currentPage = pageTotalCount;
		}
		
		int startNavi = (currentPage - 1) / naviCountPerPage * naviCountPerPage + 1;  // 네비게이터 시작 값. currentPage에서 십의자리를 가져오고 + 1;
		int endNavi = startNavi + (naviCountPerPage - 1);  // 네비게이터 끝 값	
		
		if(endNavi > pageTotalCount) {
			endNavi = pageTotalCount;
		}
		
		boolean needPrev = true;
		boolean needNext = true;

		if(startNavi == 1) {
			needPrev = false;
		} 
		
		if(endNavi == pageTotalCount) {
			needNext = false;
		}

		StringBuilder sb = new StringBuilder();
		String searchTerm = condition.get("searchTerm");
		if(needPrev) {
			sb.append("<a class='page-link' href='boardlist.bo?currentPage="+(startNavi-1)+"&search="+searchTerm+"'> Previous </a>");
		}

		for(int i = startNavi; i <= endNavi; i++) {
			if(currentPage == i) {
				sb.append("<a class='page-link' href='boardlist.bo?currentPage="+i+"&search="+searchTerm+"'> "+i+" </a>");
			} else {
				sb.append("<a class='page-link' href='boardlist.bo?currentPage="+i+"&search="+searchTerm+"'> "+i+" </a>");
			}
		}

		if(needNext) {
			sb.append("<a class='page-link' href='boardlist.bo?currentPage="+(startNavi-1)+"&search="+searchTerm+"'> Next </a>");
		}

		return sb.toString();
	}

	@Override
	public int insertArticle(BoardDTO dto) {
		return template.insert("Board.insertArticle", dto);
	}

	@Override
	public BoardDTO getArticle(int seq) {
		return (BoardDTO) template.selectList("Board.getArticle", seq).get(0);
	}

	@Override
	public int deleteArticle(int seq) {
		return template.delete("Board.deleteArticle", seq);
	}

	@Override
	public int editArticle(BoardDTO dto) {
		return template.update("Board.updateArticle", dto);
	}

	@Override
	public List<CommentDTO> getArticleComment(int seq) {
		return template.selectList("Board.getCommentData", seq);
	}

	@Override
	public int insertComment(CommentDTO dto) {
		return template.insert("Board.insertComment", dto);
	}

	@Override
	public int deleteComment(int comseq) {
		return template.delete("Board.deleteComment", comseq);
	}

	@Override
	public int updateViewCount(int seq) {
		return template.update("Board.updateViewCount", seq);
	}

	
	
	
	
}
