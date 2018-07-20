package kh.spring.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import kh.spring.dto.BoardDTO;
import kh.spring.dto.CommentDTO;
import kh.spring.interfaces.IBoardDAO;

@Component
public class BoardDAO implements IBoardDAO {

	@Autowired
	private JdbcTemplate template;

	@Override
	public List<BoardDTO> getBoardData(int startNum, int endNum) {
		String sql = "select * from (select seq,title,writer,contents,writedate,viewcount,ip, row_number() over(order by seq desc) as rnum from boarddb) where rnum between ? and ?";
		Object[] params = {startNum, endNum};
		return template.query(sql, params, new RowMapper<BoardDTO>() {

			@Override
			public BoardDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
				BoardDTO dto = new BoardDTO();
				dto.setSeq(rs.getInt("seq"));
				dto.setTitle(rs.getString("title"));
				dto.setWriter(rs.getString("writer"));
				dto.setContents(rs.getString("contents"));
				dto.setWritedate(rs.getString("writedate"));
				dto.setViewcount(rs.getInt("viewcount"));
				dto.setIp(rs.getString("ip"));
				return dto;
			}
		});
	}

	@Override
	public List<BoardDTO> getSearchData(int startNum, int endNum, String searchTerm) {
		String sql = null;

		if(searchTerm == null || searchTerm.equals("null")) {
			sql = "select * from (select seq,title,writer,contents,writedate,viewcount,ip, row_number() over(order by seq desc) as rnum from boarddb) where rnum between ? and ?";
			Object[] params = {startNum,endNum};
			return template.query(sql, params, new RowMapper<BoardDTO>() {

				@Override
				public BoardDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
					BoardDTO dto = new BoardDTO();
					dto.setSeq(rs.getInt("seq"));
					dto.setTitle(rs.getString("title"));
					dto.setWriter(rs.getString("writer"));
					dto.setContents(rs.getString("contents"));
					dto.setWritedate(rs.getString("writedate"));
					dto.setViewcount(rs.getInt("viewcount"));
					dto.setIp(rs.getString("ip"));
					return dto;
				}
			});
		} else {
			sql = "select * from (select seq,title,writer,contents,writedate,viewcount,ip, row_number() over(order by seq desc) as rnum from boarddb) where title=? and rnum between ? and ?";
			Object[] params = {searchTerm,startNum,endNum};
			return template.query(sql, params, new RowMapper<BoardDTO>() {

				@Override
				public BoardDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
					BoardDTO dto = new BoardDTO();
					dto.setSeq(rs.getInt("seq"));
					dto.setTitle(rs.getString("title"));
					dto.setWriter(rs.getString("writer"));
					dto.setContents(rs.getString("contents"));
					dto.setWritedate(rs.getString("writedate"));
					dto.setViewcount(rs.getInt("viewcount"));
					dto.setIp(rs.getString("ip"));
					return dto;
				}
			});
		}
	}

	@Override
	public String getPageNavi(int currentPage, String searchTerm) {
		String sql = null;
		int recordTotalCount = 0; // 전체 글(레코드)의 개수를 저장하는 변수
		if(searchTerm == null || searchTerm.equals("null")) {
			sql = "select count(*) totalCount from boarddb";
			recordTotalCount = template.query(sql, new RowMapper<Integer>() {
				@Override
				public Integer mapRow(ResultSet rs, int rowNum) throws SQLException {
					return rs.getInt(1);
				}

			}).get(0);
		} else {
			sql = "select count(*) totalCount from boarddb where title = ?";
			Object[] params = {searchTerm};
			recordTotalCount = template.query(sql, params, new RowMapper<Integer>() {
				@Override
				public Integer mapRow(ResultSet rs, int rowNum) throws SQLException {
					return rs.getInt(1);
				}

			}).get(0);
		}

		int recordCountPerPage = 10;  // 한 페이지에 게시글이 몇개 보일건지
		int naviCountPerPage = 10;  // 한 페이지에서 네비게이터가 몇개씩 보일건지
		int pageTotalCount = 0;  // 전체가 몇페이지로 구성될것인지

		if(recordTotalCount % recordCountPerPage > 0 ) { // 정확히 10으로 나누어 떨어지지 않음
			pageTotalCount = recordTotalCount / recordCountPerPage + 1;
		} else {
			pageTotalCount = recordTotalCount / recordCountPerPage;
		}

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
	public int insertArticle(String title, String writer, String contents, String ip) {
		String sql="insert into boarddb values(board_seq.nextval,?,?,?,sysdate,0,?)";
		return template.update(sql,title,writer,contents,ip);
	}

	@Override
	public BoardDTO getArticle(int seq) {
		String sql="select * from boarddb where seq=?";
		return template.queryForObject(sql, new RowMapper<BoardDTO>() {

			@Override
			public BoardDTO mapRow(ResultSet rs, int row) throws SQLException {
				BoardDTO dto = new BoardDTO();
				dto.setSeq(rs.getInt("seq"));
				dto.setTitle(rs.getString("title"));
				dto.setWriter(rs.getString("writer"));
				dto.setContents(rs.getString("contents"));
				dto.setWritedate(rs.getString("writedate"));
				dto.setViewcount(rs.getInt("viewcount"));
				dto.setIp(rs.getString("ip"));
				return dto;
			}
			
		},seq);
	}

	@Override
	public int deleteArticle(int seq) {
		String sql = "delete from boarddb where seq=?";
		return template.update(sql,seq);
	}

	@Override
	public int editArticle(String title, String contents, String ip, int seq) {
		String sql="update boarddb set title=?, contents=?, writedate=sysdate where seq=?";
		return template.update(sql,title, contents,seq);
	}

	@Override
	public List<CommentDTO> getArticleComment(int seq) {
		String sql = "select * from board_commentdb where article_no = ?";
		return template.query(sql, new RowMapper<CommentDTO>() {
			@Override
			public CommentDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
				CommentDTO dto = new CommentDTO();
				dto.setArticle_no(rs.getInt("article_no"));
				dto.setSeq(rs.getInt("seq"));
				dto.setContents(rs.getString("contents"));
				dto.setWriter(rs.getString("writer"));
				dto.setWritedate(rs.getString("writedate"));
				dto.setIp(rs.getString("ip"));
				return dto;
			}
			
		}, seq);
	}

	
	
	
	
}
