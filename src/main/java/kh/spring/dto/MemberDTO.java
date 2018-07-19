package kh.spring.dto;

public class MemberDTO {
	private int seq;
	private String id;
	private String pw;
	private String email;
	
	public MemberDTO() {}	
	public MemberDTO(int seq, String id, String pw, String email) {
		super();
		this.seq = seq;
		this.id = id;
		this.pw = pw;
		this.email = email;
	}
	public int getSeq() {
		return seq;
	}
	public void setSeq(int seq) {
		this.seq = seq;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPw() {
		return pw;
	}
	public void setPw(String pw) {
		this.pw = pw;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	
}
