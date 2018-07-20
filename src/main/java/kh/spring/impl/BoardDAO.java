package kh.spring.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import kh.spring.dto.BoardDTO;
import kh.spring.interfaces.IBoardDAO;

@Component
public class BoardDAO implements IBoardDAO {

	@Autowired
	private JdbcTemplate template;

	@Override
	public List<BoardDTO> getBoardData() {
		String sql = "select * from boarddb";
		return template.query(sql, new RowMapper<BoardDTO>() {

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
			sql = "select seq,title,writer,contents,writedate,viewcount,ip, row_number() over(order by seq desc) as num from boarddb) where num between ? and ? from boarddb";
			Object[] params = {startNum,endNum,searchTerm};
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
			sql = "select seq,title,writer,contents,writedate,viewcount,ip, row_number() over(order by seq desc) as num from boarddb) where num between ? and ? from boarddb where title=?";
			Object[] params = {startNum,endNum,searchTerm};
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
			sb.append("<li class='page-item'><a class='page-link' href='freeboard.bo?currentPage="+(startNavi-1)+"&search="+searchTerm+"' aria-label='Previous'><span aria-hidden=\"true\">&laquo;</span><span class=\"sr-only\">Previous</span></a></li>");
		}

		for(int i = startNavi; i <= endNavi; i++) {
			if(currentPage == i) {
				sb.append("<li class='page-item'><a class='page-link' href='freeboard.bo?currentPage="+i+"&search="+searchTerm+"'>"+i+"</a></li>");
			} else {
				sb.append("<li class='page-item'><a class='page-link' href='freeboard.bo?currentPage="+i+"&search="+searchTerm+"'> "+i+"</a></li>");
			}
		}

		if(needNext) {
			sb.append("<li class='page-item'><a class='page-link' href='freeboard.bo?currentPage="+(startNavi-1)+"&search="+searchTerm+"' aria-label='Next'><span aria-hidden='true'>&raquo;</span><span class='sr-only'>Next</span></a></li>");
		}

		return sb.toString();
	}

	@Override
	public int insertArticle(BoardDTO dto) {
		String sql="insert into boarddb values(board_seq.nextval,?,?,?,sysdate,0,?)";
		return template.update(sql,dto.getTitle(),dto.getWriter(),dto.getContents(),dto.getIp());
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
	public int editArticle(BoardDTO dto) {
		String sql="update boarddb set title=?, contents=?, writedate=sysdate where seq=?";
		return template.update(sql,dto.getTitle(),dto.getWriter(),dto.getContents(),dto.getSeq());
	}

	
	
	
	
}
