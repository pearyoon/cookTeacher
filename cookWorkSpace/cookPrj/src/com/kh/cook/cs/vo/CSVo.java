package com.kh.cook.cs.vo;

public class CSVo {

	private String qnaNo;
	private String no;
	private String writer;
	private String title;
	private String content;
	private String qnaDate;
	private String deleteYN;
	private String editDate;
	private String qnaCategory;
	private String reOX;
	
	public String getReOX() {
		return reOX;
	}
	public void setReOX(String reOX) {
		this.reOX = reOX;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	
	public String getQnaNo() {
		return qnaNo;
	}
	public void setQnaNo(String qnaNo) {
		this.qnaNo = qnaNo;
	}
	public String getNo() {
		return no;
	}
	public void setNo(String no) {
		this.no = no;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getQnaDate() {
		return qnaDate;
	}
	public void setQnaDate(String qnaDate) {
		this.qnaDate = qnaDate;
	}
	public String getDeleteYN() {
		return deleteYN;
	}
	public void setDeleteYN(String deleteYN) {
		this.deleteYN = deleteYN;
	}
	public String getEditDate() {
		return editDate;
	}
	public void setEditDate(String editDate) {
		this.editDate = editDate;
	}
	public String getQnaCategory() {
		return qnaCategory;
	}
	public void setQnaCategory(String qnaCategory) {
		this.qnaCategory = qnaCategory;
	}

	
	@Override
	public String toString() {
		return "CSVo [qnaNo=" + qnaNo + ", no=" + no + ", writer=" + writer + ", title=" + title + ", content="
				+ content + ", qnaDate=" + qnaDate + ", deleteYN=" + deleteYN + ", editDate=" + editDate
				+ ", qnaCategory=" + qnaCategory + ", reOX=" + reOX + "]";
	}
	public CSVo(String qnaNo, String no, String writer, String title, String content, String qnaDate, String deleteYN,
			String editDate, String qnaCategory, String reOX) {
		super();
		this.qnaNo = qnaNo;
		this.no = no;
		this.writer = writer;
		this.title = title;
		this.content = content;
		this.qnaDate = qnaDate;
		this.deleteYN = deleteYN;
		this.editDate = editDate;
		this.qnaCategory = qnaCategory;
		this.reOX = reOX;
	}
	public CSVo() {
		super();
	}
	
	
	
}
