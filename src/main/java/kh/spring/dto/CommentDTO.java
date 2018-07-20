package kh.spring.dto;

public class CommentDTO {
	private int article_no;
	private int seq;
	private String contents;
	private String writer;
	private String writedate;
	private String ip;
	
	
	public CommentDTO() {}
	public CommentDTO(int article_no, int seq, String contents, String writer, String writedate, String ip) {
		super();
		this.article_no = article_no;
		this.seq = seq;
		this.contents = contents;
		this.writer = writer;
		this.writedate = writedate;
		this.ip = ip;
	}
	public int getArticle_no() {
		return article_no;
	}
	public void setArticle_no(int article_no) {
		this.article_no = article_no;
	}
	public int getSeq() {
		return seq;
	}
	public void setSeq(int seq) {
		this.seq = seq;
	}
	public String getContents() {
		return contents;
	}
	public void setContents(String contents) {
		this.contents = contents;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public String getWritedate() {
		return writedate;
	}
	public void setWritedate(String writedate) {
		this.writedate = writedate;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
}
